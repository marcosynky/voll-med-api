package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.dto.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.dto.DadosDetalhamentoConsulta;
import med.voll.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // Indica que esta classe é um serviço, que é gerido pelo Spring (geralmente contém a lógica de negócios)
public class AgendaConsultaService {

    // Injeção de dependências para acessar os repositórios de consulta, médico e paciente
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    // Lista de validadores que serão usados para validar o agendamento da consulta
    @Autowired
    private List<ValidadorAgendamentoConsulta> validadoresAgendamento;

    // Método principal que agenda a consulta
    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        // Verifica se o paciente informado existe no banco de dados
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe!");  // Lança uma exceção se o paciente não for encontrado
        }

        // Verifica se o médico informado existe no banco de dados (caso o médico tenha sido especificado)
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do médico informado não existe!");  // Lança uma exceção se o médico não for encontrado
        }

        // Executa validações adicionais para o agendamento utilizando os validadores fornecidos
        validadoresAgendamento.forEach(v -> v.validar(dados));

        // Recupera o paciente do banco de dados
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());

        // Escolhe o médico (se foi fornecido ou se precisa ser escolhido aleatoriamente)
        var medico = escolherMedico(dados);

        // Se não encontrar um médico disponível, lança uma exceção
        if (medico == null) {
            throw new ValidacaoException("Não existe médico disponível nessa data!");
        }

        // Cria uma nova instância de consulta com o médico, paciente e data
        var consulta = new Consulta(medico, paciente, dados.data());

        // Salva a consulta no banco de dados
        consultaRepository.save(consulta);

        // Retorna os detalhes da consulta agendada
        return new DadosDetalhamentoConsulta(consulta);
    }

    // Método para escolher o médico para a consulta
    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        // Se o ID do médico foi informado, recupera o médico do banco de dados
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        // Se o médico não foi escolhido, verifica se a especialidade foi fornecida
        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");  // Lança uma exceção se a especialidade não for informada
        }

        // Caso o médico não tenha sido escolhido, escolhe um médico aleatório disponível para a especialidade e data informadas
        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }
}
