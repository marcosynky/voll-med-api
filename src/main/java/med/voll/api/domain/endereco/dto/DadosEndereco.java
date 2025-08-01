package med.voll.api.domain.endereco.dto;

import jakarta.validation.constraints.NotBlank;  // Validação para garantir que o campo não esteja em branco
import jakarta.validation.constraints.Pattern;  // Validação para garantir que o campo siga um padrão específico

// A classe 'record' define um tipo imutável de dados, que é uma boa escolha para objetos simples de transferência de dados (DTOs).
public record DadosEndereco(
        @NotBlank  // Garante que o logradouro não seja nulo nem em branco
        String logradouro,  // Nome da rua, avenida, etc.

        @NotBlank  // Garante que o bairro não seja nulo nem em branco
        String bairro,  // Nome do bairro

        @NotBlank  // Garante que o CEP não seja nulo nem em branco
        @Pattern(regexp = "\\d{8}")  // Garante que o CEP seja composto por exatamente 8 dígitos
        String cep,  // CEP (com 8 dígitos, sem traço)

        @NotBlank  // Garante que a cidade não seja nula nem em branco
        String cidade,  // Nome da cidade

        @NotBlank  // Garante que a UF não seja nula nem em branco
        String uf,  // Unidade Federativa (estado) da cidade

        String complemeto,  // Complemento do endereço (opcional)
        String numero) {  // Número da residência (opcional)
}
