package med.voll.api.domain.medico;

import med.voll.api.domain.ValidacaoException;  // Exceção personalizada para validações
import med.voll.api.domain.medico.dto.DadosCadastroMedico;  // DTO para cadastro de médicos
import med.voll.api.domain.medico.dto.DadosDetalhamentoMedico;  // DTO para detalhamento do médico
import org.springframework.beans.factory.annotation.Autowired;  // Anotação para injeção de dependência
import org.springframework.stereotype.Service;  // Anotação para indicar que a classe é um serviço

@Service  // Anotação que marca a classe como um serviço gerenciado pelo Spring
public class CadastroMedicoService {

    @Autowired  // Injeta o repositório de médicos para interação com o banco de dados
    private MedicoRepository repository;

    // Método para cadastrar um novo médico
    public DadosDetalhamentoMedico cadastrar(DadosCadastroMedico dados) {
        // Verifica se já existe outro médico com o mesmo email ou CRM
        var jaCadastrado = repository.existsByEmailOrCrm(dados.email(), dados.crm());

        // Se o médico já existir, lança uma exceção de validação
        if (jaCadastrado) {
            throw new ValidacaoException("Já existe outro médico com o email ou crm informado!");
        }

        // Cria o objeto médico com os dados recebidos do DTO
        var medico = new Medico(dados);

        // Salva o novo médico no banco de dados
        repository.save(medico);

        // Retorna os detalhes do médico cadastrado
        return new DadosDetalhamentoMedico(medico);
    }

}
