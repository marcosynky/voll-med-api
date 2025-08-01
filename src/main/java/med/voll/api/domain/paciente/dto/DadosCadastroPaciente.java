package med.voll.api.domain.paciente.dto;

import jakarta.validation.Valid;  // Importa a anotação para validação de objetos aninhados
import jakarta.validation.constraints.Email;  // Importa a anotação para validar o e-mail
import jakarta.validation.constraints.NotBlank;  // Importa a anotação para garantir que o campo não seja vazio
import jakarta.validation.constraints.NotNull;  // Importa a anotação para garantir que o campo não seja nulo
import jakarta.validation.constraints.Pattern;  // Importa a anotação para validar padrões específicos (como CPF)
import med.voll.api.domain.endereco.dto.DadosEndereco;  // Importa o DTO de endereço

// 'DadosCadastroPaciente' é um DTO utilizado para transferir os dados de cadastro de um paciente
public record DadosCadastroPaciente(
        @NotBlank  // Garante que o campo 'nome' não seja vazio
        String nome,  // Nome do paciente

        @NotBlank  // Garante que o campo 'email' não seja vazio
        @Email  // Valida o formato do e-mail
        String email,  // E-mail do paciente

        @NotBlank  // Garante que o campo 'telefone' não seja vazio
        String telefone,  // Telefone do paciente

        @NotBlank  // Garante que o campo 'cpf' não seja vazio
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")  // Valida o formato do CPF (com ou sem pontos e traço)
        String cpf,  // CPF do paciente (formato: 123.456.789-01 ou 12345678901)

        @NotNull  // Garante que o campo 'endereco' não seja nulo
        @Valid  // Valida o objeto aninhado (endereço) de acordo com as restrições definidas no DTO 'DadosEndereco'
        DadosEndereco endereco) {  // Endereço do paciente
}
