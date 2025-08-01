package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.consulta.dto.DadosAgendamentoConsulta;  // DTO que contém os dados do agendamento da consulta

// Interface que define o contrato para validadores de agendamento de consulta
public interface ValidadorAgendamentoConsulta {

    // Método que será implementado por cada validador, responsável por validar o agendamento de consulta
    void validar(DadosAgendamentoConsulta dados);

}
