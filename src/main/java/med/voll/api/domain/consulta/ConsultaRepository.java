package med.voll.api.domain.consulta;

import org.springframework.data.domain.Page;  // Para a utilização de paginação
import org.springframework.data.domain.Pageable;  // Para a utilização de paginação
import org.springframework.data.jpa.repository.JpaRepository;  // Interface base para os repositórios JPA

import java.time.LocalDateTime;  // Para trabalhar com data e hora

// A interface extends JpaRepository, permitindo o acesso a métodos padrão de persistência de dados
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    // Verifica se já existe uma consulta agendada para um paciente específico, dentro de um intervalo de horário
    boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    // Verifica se já existe uma consulta agendada para um médico específico em uma data exata
    boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime data);

    // Retorna todas as consultas com data maior que a informada, com paginação aplicada
    Page<Consulta> findAllByDataGreaterThan(LocalDateTime data, Pageable paginacao);
}
