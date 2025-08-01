package med.voll.api.domain.consulta.dto;

import jakarta.validation.constraints.Future;  // Validação para garantir que a data seja no futuro
import jakarta.validation.constraints.NotNull;  // Validação para garantir que o campo não seja nulo
import med.voll.api.domain.medico.Especialidade;  // Enum para representar especialidades médicas

import java.time.LocalDateTime;  // Para trabalhar com data e hora

// A classe 'record' é uma estrutura imutável que facilita a criação de objetos de transferência de dados (DTOs).
public record DadosAgendamentoConsulta(
        Long idMedico,  // ID do médico (pode ser nulo se o paciente escolher um médico aleatório)

        @NotNull  // Garante que o ID do paciente não seja nulo
        Long idPaciente,  // ID do paciente que está agendando a consulta

        @NotNull  // Garante que a data não seja nula
        @Future  // Garante que a data seja no futuro
        LocalDateTime data,  // Data e hora da consulta (não pode ser no passado)

        Especialidade especialidade  // Especialidade médica para a consulta, caso o médico não tenha sido escolhido
) {
}
