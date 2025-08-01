package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendaConsultaService;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.dto.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.dto.DadosDetalhamentoConsulta;
import med.voll.api.domain.consulta.dto.DadosListagemConsulta;
import med.voll.api.domain.consulta.dto.DadosRelatorioConsultaMensal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

// Anotação que indica que esta classe é um controlador REST
@RestController
@RequestMapping("consultas") // Define o caminho base para os endpoints
public class ConsultaController {

    // Injeta o serviço responsável pela lógica de agendamento de consultas
    @Autowired
    private AgendaConsultaService agendaConsultas;

    // Injeta o repositório de consultas
    @Autowired
    private ConsultaRepository consultaRepository;

    // Endpoint para agendar uma nova consulta
    @PostMapping
    @Transactional  // Garante que a operação será executada dentro de uma transação
    public ResponseEntity<DadosDetalhamentoConsulta> agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        // Chama o serviço para agendar a consulta
        var detalhesAgendamento = agendaConsultas.agendar(dados);
        // Retorna a resposta com os detalhes do agendamento da consulta
        return ResponseEntity.ok(detalhesAgendamento);
    }

    // Endpoint para listar as próximas consultas agendadas
    @GetMapping
    public ResponseEntity<Page<DadosListagemConsulta>> listarProximasConsultas(
            @PageableDefault(size = 10, sort = {"data"}) Pageable paginacao) {
        // Busca as consultas com data maior que a data atual
        var proximasConsultas = consultaRepository.findAllByDataGreaterThan(LocalDateTime.now(), paginacao)
                .map(DadosListagemConsulta::new);
        // Retorna as consultas paginadas
        return ResponseEntity.ok(proximasConsultas);
    }

    // Endpoint para gerar um relatório mensal de consultas
    @GetMapping("/relatorio-mensal/{mes}")
    public ResponseEntity<List<DadosRelatorioConsultaMensal>> gerarRelatorioConsultaMensal(@PathVariable YearMonth mes) {
        LocalDateTime inicioMes = mes.atDay(1).atStartOfDay();
        LocalDateTime fimMes = mes.atEndOfMonth().atTime(23, 59, 59);
        List<DadosRelatorioConsultaMensal> relatorio = consultaRepository.gerarRelatorioConsultaMensal(inicioMes, fimMes);
        return ResponseEntity.ok(relatorio);
    }

}
