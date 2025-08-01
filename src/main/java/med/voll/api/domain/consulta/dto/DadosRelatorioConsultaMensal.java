package med.voll.api.domain.consulta.dto;

// A classe record define um tipo imutável de dados, que é uma boa escolha para objetos simples de transferência de dados (DTOs).
public record DadosRelatorioConsultaMensal(String nome, String crm, Long quantidadeConsultasNoMes) {
    // 'nome' representa o nome do médico
    // 'crm' representa o CRM (registro profissional) do médico
    // 'quantidadeConsultasNoMes' representa o número de consultas realizadas pelo médico no mês
}

