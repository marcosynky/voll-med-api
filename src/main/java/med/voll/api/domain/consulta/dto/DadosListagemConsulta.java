package med.voll.api.domain.consulta.dto;

import med.voll.api.domain.consulta.Consulta;  // Entidade que representa uma consulta
import med.voll.api.domain.medico.Especialidade;  // Enum que representa as especialidades médicas

import java.time.LocalDateTime;  // Para trabalhar com data e hora

// A classe 'record' é uma estrutura imutável que facilita a criação de objetos de transferência de dados (DTOs)
public record DadosListagemConsulta(Long id, LocalDateTime data, Especialidade especialidade, String medico, String paciente) {

    // Construtor adicional que cria um objeto DadosListagemConsulta a partir de uma instância da entidade Consulta
    public DadosListagemConsulta(Consulta consulta) {
        // Inicializa o record com os dados da consulta (id, data, especialidade, nome do médico e nome do paciente)
        this(consulta.getId(), consulta.getData(), consulta.getMedico().getEspecialidade(), consulta.getMedico().getNome(), consulta.getPaciente().getNome());
    }

}
