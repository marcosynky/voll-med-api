package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.CadastroMedicoService;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.medico.dto.DadosAtualizacaoMedico;
import med.voll.api.domain.medico.dto.DadosCadastroMedico;
import med.voll.api.domain.medico.dto.DadosDetalhamentoMedico;
import med.voll.api.domain.medico.dto.DadosListagemMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")  // Define o caminho base para os endpoints relacionados aos médicos
public class MedicoController {

    // Injeta o repositório de médicos para acessar os dados no banco
    @Autowired
    private MedicoRepository repository;

    // Injeta o serviço que realiza a lógica de cadastro de médicos
    @Autowired
    private CadastroMedicoService service;

    // Endpoint para cadastrar um novo médico
    @PostMapping
    @Transactional  // Garante que a operação de cadastro será realizada dentro de uma transação
    public ResponseEntity<DadosDetalhamentoMedico> cadastrar(
            @RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        // Chama o serviço para cadastrar o médico
        var detalhesMedico = service.cadastrar(dados);

        // Cria a URI do novo recurso (médico cadastrado)
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(detalhesMedico.id()).toUri();

        // Retorna uma resposta 201 (Created) com a URI do novo recurso
        return ResponseEntity.created(uri).body(detalhesMedico);
    }

    // Endpoint para listar os médicos com paginação
    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        // Busca todos os médicos no repositório com a paginação aplicada
        var medicos = repository.findAll(paginacao).map(DadosListagemMedico::new);

        // Retorna uma resposta com a lista de médicos paginada
        return ResponseEntity.ok(medicos);
    }

    // Endpoint para detalhar um médico específico pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedico> detalhar(@PathVariable Long id) {
        // Busca o médico pelo ID no repositório
        var medico = repository.getReferenceById(id);

        // Retorna os detalhes do médico encontrado
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    // Endpoint para atualizar as informações de um médico
    @PutMapping
    @Transactional  // Garante que a operação de atualização será realizada dentro de uma transação
    public ResponseEntity<DadosDetalhamentoMedico> atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        // Busca o médico pelo ID no repositório
        var medico = repository.getReferenceById(dados.id());

        // Atualiza as informações do médico com os dados recebidos
        medico.atualizarInformacoes(dados);

        // Retorna os detalhes do médico atualizado
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    // Endpoint para excluir (desativar) um médico
    @DeleteMapping("/{id}")
    @Transactional  // Garante que a operação de exclusão será realizada dentro de uma transação
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        // Busca o médico pelo ID no repositório
        var medico = repository.getReferenceById(id);

        // Desativa o médico (não exclui fisicamente)
        medico.desativar();

        // Retorna uma resposta 204 (No Content) indicando que a operação foi bem-sucedida, mas não há conteúdo a ser retornado
        return ResponseEntity.noContent().build();
    }
}
