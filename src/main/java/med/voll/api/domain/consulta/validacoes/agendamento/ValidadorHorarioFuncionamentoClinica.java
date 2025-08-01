package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;  // Exceção personalizada para validação de erros de agendamento
import med.voll.api.domain.consulta.dto.DadosAgendamentoConsulta;  // DTO contendo os dados do agendamento da consulta
import org.springframework.stereotype.Component;  // Anotação para que a classe seja registrada como um componente Spring
import java.time.DayOfWeek;  // Para trabalhar com o dia da semana e verificar se é domingo

@Component  // Torna essa classe um componente Spring, permitindo injeção de dependência
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsulta {

    // Método que valida se o agendamento está dentro do horário de funcionamento da clínica
    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();  // Obtém a data e hora da consulta do DTO

        // Verifica se o agendamento é para um domingo
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);

        // Verifica se o horário da consulta é antes da abertura da clínica (antes das 07:00)
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;

        // Verifica se o horário da consulta é depois do encerramento da clínica (depois das 18:00)
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;

        // Se for domingo ou se o horário estiver fora do funcionamento, lança uma exceção
        if (domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }
    }

}
