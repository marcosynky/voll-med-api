package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;  // Exceção personalizada para validações
import med.voll.api.domain.consulta.ConsultaRepository;  // Repositório para consultas
import med.voll.api.domain.consulta.dto.DadosAgendamentoConsulta;  // DTO que contém os dados do agendamento da consulta
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component  // Indica que esta classe é um componente gerenciado pelo Spring, podendo ser injetada em outros lugares
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoConsulta {

    // Injeta o repositório de consultas para verificar as consultas do paciente no banco de dados
    @Autowired
    private ConsultaRepository repository;

    // Método que valida se o paciente já possui uma consulta agendada no mesmo dia
    public void validar(DadosAgendamentoConsulta dados) {
        // Define o intervalo de horários (das 07:00 até as 18:00) para verificar se o paciente tem outra consulta agendada
        var primeiroHorario = dados.data().withHour(7);  // Define o primeiro horário possível (07:00) para o agendamento
        var ultimoHorario = dados.data().withHour(18);   // Define o último horário possível (18:00) para o agendamento

        // Verifica se o paciente já possui uma consulta agendada dentro do intervalo de horários definido
        var pacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);

        // Se o paciente já possui uma consulta agendada, lança uma exceção com a mensagem apropriada
        if (pacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia");
        }
    }

}
