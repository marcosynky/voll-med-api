package med.voll.api.domain.medico;

import org.springframework.data.jpa.repository.JpaRepository; // Interface base para os repositórios JPA
import org.springframework.data.jpa.repository.Query; // Anotação para consultas personalizadas em JPQL

import java.time.LocalDateTime; // Para trabalhar com data e hora

// A interface MedicoRepository extends JpaRepository, permitindo o acesso a métodos padrão de persistência de dados
public interface MedicoRepository extends JpaRepository<Medico, Long> {
// Anotação para consultas personalizadas em JPQL
    @Query("""
            select m from Medico m
            where
            m.ativo = true
            and
            m.especialidade = :especialidade
            and
            m.id not in(
                select c.medico.id from Consulta c
                where
                c.data = :data
            )
            order by rand()
            limit 1
        """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);
// Anotação para consultas personalizadas em JPQL
    @Query("""
            select m.ativo
            from Medico m
            where
            m.id = :id
            """)
    boolean findAtivoById(Long id);// Anotação para consultas personalizadas em JPQL

    boolean existsByEmailOrCrm(String email, String crm); // Anotação para consultas personalizadas em JPQL

}
