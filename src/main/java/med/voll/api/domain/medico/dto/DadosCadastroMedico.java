package med.voll.api.domain.medico.dto;

import jakarta.validation.Valid;  // Importa a validação de objetos aninhados
import jakarta.validation.constraints.Email;  // Valida se o e-mail tem o formato correto
import jakarta.validation.constraints.NotBlank;  // Valida se o campo não está em branco
import jakarta.validation.constraints.NotNull;  // Valida se o campo não é nulo
import jakarta.validation.constraints.Pattern;  // Valida se o campo corresponde ao padrão de expressão regular
import med.voll.api.domain.endereco.dto.DadosEndereco;  // Importa o DTO para o endereço
import med.voll.api.domain.medico.Especialidade;  // Importa a enum de especialidades médicas

// 'DadosCadastroMedico' é um DTO usado para transferir os dados necessários para cadastrar um médico
public record DadosCadastroMedico(
        @NotBlank  // Garante que o nome do médico não seja nulo nem vazio
        String nome,  // Nome completo do médico

        @NotBlank  // Garante que o e-mail do médico não seja nulo nem vazio
        @Email  // Garante que o formato do e-mail seja válido
        String email,  // E-mail do médico

        @NotBlank  // Garante que o telefone do médico não seja nulo nem vazio
        String telefone,  // Número de telefone do médico

        @NotBlank  // Garante que o CRM não seja nulo nem vazio
        @Pattern(regexp = "\\d{4,6}")  // Garante que o CRM seja composto por 4 a 6 dígitos
        String crm,  // Número de CRM do médico (registro profissional)

        @NotNull  // Garante que a especialidade não seja nula
        Especialidade especialidade,  // Especialidade do médico (cardiologia, ortopedia, etc.)

        @NotNull  // Garante que o endereço não seja nulo
        @Valid  // Valida o objeto endereço aninhado (validando seus próprios campos)
        DadosEndereco endereco) {  // Endereço do médico, com todos os dados necessários
}
