package med.voll.api.domain.medico.dto;

import jakarta.validation.constraints.NotNull;  // Importa a validação para garantir que o campo não seja nulo
import med.voll.api.domain.endereco.dto.DadosEndereco;  // Importa o DTO para o endereço

// 'DadosAtualizacaoMedico' é um DTO usado para transferir os dados necessários para atualizar as informações de um médico
public record DadosAtualizacaoMedico(
        @NotNull  // Garante que o 'id' do médico não seja nulo, pois é necessário para identificar qual médico será atualizado
        Long id,  // ID do médico que será atualizado

        String nome,  // Novo nome do médico, opcional
        String telefone,  // Novo telefone do médico, opcional
        DadosEndereco endereco) {  // Novo endereço do médico, opcional
}
