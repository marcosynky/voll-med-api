package med.voll.api.domain.paciente;

import med.voll.api.domain.ValidacaoException;  // Importa a classe de exceção personalizada para validação
import med.voll.api.domain.paciente.dto.DadosCadastroPaciente;  // Importa o DTO de cadastro de paciente
import med.voll.api.domain.paciente.dto.DadosDetalhamentoPaciente;  // Importa o DTO de detalhamento do paciente
import org.springframework.beans.factory.annotation.Autowired;  // Importa a anotação para injeção de dependências
import org.springframework.stereotype.Service;  // Marca a classe como um serviço do Spring

@Service  // Marca a classe como um serviço gerenciado pelo Spring
public class CadastroPacienteService {

    @Autowired  // Injeta o repositório de pacientes no serviço
    private PacienteRepository repository;

    // Método responsável pelo cadastro de um paciente
    public DadosDetalhamentoPaciente cadastrar(DadosCadastroPaciente dados) {
        // Verifica se já existe um paciente com o mesmo email ou CPF
        var jaCadastrado = repository.existsByEmailOrCpf(dados.email(), dados.cpf());
        if (jaCadastrado) {
            // Lança uma exceção de validação se o paciente já estiver cadastrado
            throw new ValidacaoException("Já existe outro paciente com o email ou cpf informado!");
        }

        // Cria um novo paciente a partir dos dados fornecidos
        var paciente = new Paciente(dados);
        // Salva o paciente no banco de dados
        repository.save(paciente);
        // Retorna os detalhes do paciente após o cadastro
        return new DadosDetalhamentoPaciente(paciente);
    }

}
