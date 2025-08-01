package med.voll.api.domain.medico.dto;

import med.voll.api.domain.endereco.Endereco;  // Importa a classe Endereco, que é um componente embutido no Medico
import med.voll.api.domain.medico.Especialidade;  // Importa a enum Especialidade, representando a especialidade médica
import med.voll.api.domain.medico.Medico;  // Importa a classe Medico, a entidade que está sendo detalhada

// 'DadosDetalhamentoMedico' é um DTO utilizado para retornar todos os dados de um médico de forma detalhada
public record DadosDetalhamentoMedico(
        Long id,  // ID do médico
        Boolean ativo,  // Status de ativo ou inativo do médico
        String nome,  // Nome do médico
        String email,  // E-mail do médico
        String crm,  // CRM do médico (registro profissional)
        String telefone,  // Telefone do médico
        Especialidade especialidade,  // Especialidade do médico
        Endereco endereco  // Endereço do médico, embutido como um objeto Endereco
) {

    // Construtor que cria um 'DadosDetalhamentoMedico' a partir de um objeto Medico
    public DadosDetalhamentoMedico(Medico medico) {
        // Inicializa o 'DadosDetalhamentoMedico' com as informações extraídas do objeto Medico
        this(medico.getId(),
                medico.getAtivo(),
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getTelefone(),
                medico.getEspecialidade(),
                medico.getEndereco());
    }
}
