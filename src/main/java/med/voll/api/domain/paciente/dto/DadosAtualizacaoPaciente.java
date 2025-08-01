package med.voll.api.domain.paciente.dto;

import jakarta.validation.constraints.NotNull;  // Importa a anotação para garantir que o campo não seja nulo
import med.voll.api.domain.endereco.dto.DadosEndereco;  // Importa o DTO para o endereço do paciente

// 'DadosAtualizacaoPaciente' é um DTO utilizado para transferir os dados de atualização de um paciente
public record DadosAtualizacaoPaciente(
        @NotNull  // Garante que o campo 'id' não seja nulo
        Long id,  // ID do paciente que será atualizado

        String nome,  // Novo nome do paciente (opcional, pois pode ser nulo)
        String telefone,  // Novo telefone do paciente (opcional)
        DadosEndereco endereco) {  // Novo endereço do paciente (opcional, mas deve ser validado)

}
