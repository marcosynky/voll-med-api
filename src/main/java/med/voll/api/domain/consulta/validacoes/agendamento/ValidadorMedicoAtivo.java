package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;  // Exceção personalizada para validação de erros de agendamento
import med.voll.api.domain.consulta.dto.DadosAgendamentoConsulta;  // DTO contendo os dados do agendamento da consulta
import med.voll.api.domain.medico.MedicoRepository;  // Repositório para verificar o status de médicos
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component  // A anotação @Component torna essa classe um componente Spring gerenciado pelo container de dependências
public class ValidadorMedicoAtivo implements ValidadorAgendamentoConsulta {

    // Injeção do repositório de médicos para consultar se o médico está ativo
    @Autowired
    private MedicoRepository repository;

    // Método que valida se o médico está ativo antes de agendar a consulta
    public void validar(DadosAgendamentoConsulta dados) {
        // Se o id do médico não for informado, não há necessidade de verificar o estado do médico
        if (dados.idMedico() == null) {
            return;  // Se o médico não for escolhido, a validação não é necessária
        }

        // Verifica se o médico especificado está ativo no banco de dados
        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());

        // Se o médico não estiver ativo (por exemplo, foi excluído ou desativado), lança uma exceção
        if (!medicoEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico excluído");
        }
    }

}
