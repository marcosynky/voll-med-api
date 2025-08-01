package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;  // Exceção personalizada para validações
import med.voll.api.domain.consulta.dto.DadosAgendamentoConsulta;  // DTO contendo os dados do agendamento da consulta
import med.voll.api.domain.paciente.PacienteRepository;  // Repositório para acessar os dados do paciente
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component  // Indica que essa classe é um componente Spring e será gerida pelo container de dependências do Spring
public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsulta {

    // Injeta o repositório de pacientes para verificar o status do paciente
    @Autowired
    private PacienteRepository repository;

    // Método que valida se o paciente está ativo antes de agendar a consulta
    public void validar(DadosAgendamentoConsulta dados) {
        // Chama o repositório para verificar se o paciente está ativo
        var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());

        // Se o paciente não estiver ativo, lança uma exceção personalizada
        if (!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído");
        }
    }

}
