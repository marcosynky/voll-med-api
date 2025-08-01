package med.voll.api.domain.paciente.dto;

import med.voll.api.domain.paciente.Paciente;  // Importa a classe 'Paciente' para construir o DTO a partir dela

// 'DadosListagemPaciente' é um DTO utilizado para transferir os dados de um paciente, especialmente para listagens
public record DadosListagemPaciente(
        Long id,  // ID do paciente
        Boolean ativo,  // Estado do paciente (ativo ou inativo)
        String nome,  // Nome do paciente
        String email,  // E-mail do paciente
        String cpf) {  // CPF do paciente

    // Construtor do DTO que cria uma instância de 'DadosListagemPaciente' a partir de um objeto 'Paciente'
    public DadosListagemPaciente(Paciente paciente) {
        // Preenche os campos do DTO com os dados do paciente
        this(paciente.getId(), paciente.getAtivo(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
