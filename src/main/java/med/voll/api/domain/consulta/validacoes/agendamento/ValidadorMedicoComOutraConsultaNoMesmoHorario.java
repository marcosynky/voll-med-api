package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;  // Exceção personalizada para validação de erros de agendamento
import med.voll.api.domain.consulta.ConsultaRepository;  // Repositório para interagir com as consultas no banco de dados
import med.voll.api.domain.consulta.dto.DadosAgendamentoConsulta;  // DTO que contém os dados do agendamento da consulta
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component  // A anotação @Component torna essa classe um componente do Spring para ser gerenciada pelo container de dependências
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoConsulta {

    // Injeção do repositório de consultas para verificar as consultas do médico
    @Autowired
    private ConsultaRepository repository;

    // Método que valida se o médico já possui uma consulta agendada no mesmo horário
    public void validar(DadosAgendamentoConsulta dados) {
        // Verifica se o médico já possui uma consulta agendada para o horário informado
        var medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());

        // Se o médico já tiver outra consulta nesse horário, lança uma exceção
        if (medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("Médico já possui outra consulta agendada nesse mesmo horário");
        }
    }

}
