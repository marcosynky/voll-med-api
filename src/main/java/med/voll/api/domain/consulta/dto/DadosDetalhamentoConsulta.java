package med.voll.api.domain.consulta.dto;

import med.voll.api.domain.consulta.Consulta;  // Entidade que representa uma consulta
import java.time.LocalDateTime;  // Para trabalhar com data e hora

// A classe record define um tipo imutável de dados, que é uma boa escolha para objetos simples de transferência de dados (DTOs).
public record DadosDetalhamentoConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {

    // Construtor adicional que cria um objeto DadosDetalhamentoConsulta a partir de uma instância da entidade Consulta
    public DadosDetalhamentoConsulta(Consulta consulta) {
        // Inicializa o record com os dados da consulta (id, id do médico, id do paciente e data da consulta)
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }

}
