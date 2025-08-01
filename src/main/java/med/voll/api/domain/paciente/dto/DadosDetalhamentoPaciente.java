package med.voll.api.domain.paciente.dto;

import med.voll.api.domain.endereco.Endereco;  // Importa a classe 'Endereco' para incluir no DTO
import med.voll.api.domain.paciente.Paciente;  // Importa a classe 'Paciente' para mapear os dados do paciente

// 'DadosDetalhamentoPaciente' é um DTO utilizado para transferir todos os dados detalhados de um paciente
public record DadosDetalhamentoPaciente(
        Long id,  // ID do paciente
        Boolean ativo,  // Estado de atividade do paciente (se está ativo ou inativo)
        String nome,  // Nome completo do paciente
        String email,  // E-mail do paciente
        String cpf,  // CPF do paciente
        String telefone,  // Telefone do paciente
        Endereco endereco) {  // Endereço do paciente (embutido no DTO)

    // Construtor que cria uma instância de 'DadosDetalhamentoPaciente' a partir de um objeto 'Paciente'
    public DadosDetalhamentoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getAtivo(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(), paciente.getEndereco());
    }
}
