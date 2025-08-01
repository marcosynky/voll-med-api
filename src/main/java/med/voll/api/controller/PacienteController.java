package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.paciente.CadastroPacienteService;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.domain.paciente.dto.DadosAtualizacaoPaciente;
import med.voll.api.domain.paciente.dto.DadosCadastroPaciente;
import med.voll.api.domain.paciente.dto.DadosDetalhamentoPaciente;
import med.voll.api.domain.paciente.dto.DadosListagemPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

// Anotação que define esta classe como um controlador REST para a rota "/pacientes"
@RestController
@RequestMapping("pacientes")
public class PacienteController {

    // Injeção de dependência do repositório de pacientes
    @Autowired
    private PacienteRepository repository;

    // Injeção de dependência do serviço de cadastro de pacientes
    @Autowired
    private CadastroPacienteService service;

    // Endpoint para cadastrar um novo paciente
    @PostMapping
    @Transactional  // Garante que a operação será executada dentro de uma transação
    public ResponseEntity<DadosDetalhamentoPaciente> cadastrar(
            @RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
        // Chama o serviço de cadastro, passando os dados do paciente
        var detalhesPaciente = service.cadastrar(dados);

        // Gera a URI do novo paciente cadastrado
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(detalhesPaciente.id()).toUri();

        // Retorna uma resposta 201 (Created) com a URI do novo recurso
        return ResponseEntity.created(uri).body(detalhesPaciente);
    }

    // Endpoint para listar pacientes, com paginação
    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listar(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        // Busca os pacientes no repositório com a paginação aplicada
        var pacientes = repository.findAll(paginacao).map(DadosListagemPaciente::new);

        // Retorna os pacientes encontrados, com a paginação
        return ResponseEntity.ok(pacientes);
    }

    // Endpoint para detalhar as informações de um paciente pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> detalhar(@PathVariable Long id) {
        // Busca o paciente pelo ID
        var paciente = repository.getReferenceById(id);

        // Retorna os detalhes do paciente
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    // Endpoint para atualizar as informações de um paciente
    @PutMapping
    @Transactional  // Garante que a operação de atualização será executada dentro de uma transação
    public ResponseEntity<DadosDetalhamentoPaciente> atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
        // Busca o paciente pelo ID para atualização
        var paciente = repository.getReferenceById(dados.id());

        // Atualiza as informações do paciente com os dados fornecidos
        paciente.atualizarInformacoes(dados);

        // Retorna os detalhes do paciente atualizado
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    // Endpoint para excluir um paciente (desativar)
    @DeleteMapping("/{id}")
    @Transactional  // Garante que a operação de exclusão será executada dentro de uma transação
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        // Busca o paciente pelo ID para desativação
        var paciente = repository.getReferenceById(id);

        // Desativa o paciente (não o exclui fisicamente do banco)
        paciente.desativar();

        // Retorna uma resposta 204 (No Content) indicando que a operação foi bem-sucedida, mas sem conteúdo
        return ResponseEntity.noContent().build();
    }

}
