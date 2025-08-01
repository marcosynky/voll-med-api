package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;  // Exceção personalizada para validação de erros de agendamento
import med.voll.api.domain.consulta.dto.DadosAgendamentoConsulta;  // DTO que contém os dados do agendamento da consulta
import org.springframework.stereotype.Component;  // Anotação para registrar a classe como um componente Spring
import java.time.Duration;  // Classe usada para calcular a diferença entre datas e horários
import java.time.LocalDateTime;  // Para trabalhar com data e hora

@Component("ValidadorHorarioAntecedenciaAgendamento")  // A classe é registrada como um componente Spring com um nome específico
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoConsulta {

    // Método que valida se a consulta está sendo agendada com antecedência mínima de 30 minutos
    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();  // Obtém a data e hora da consulta fornecida
        var agora = LocalDateTime.now();  // Obtém a data e hora atual
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();  // Calcula a diferença entre o horário atual e a data da consulta em minutos

        // Se a diferença for menor que 30 minutos, lança uma exceção
        if (diferencaEmMinutos < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }

}
