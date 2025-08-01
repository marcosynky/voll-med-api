package med.voll.api.domain.paciente;

import org.springframework.data.jpa.repository.JpaRepository;  // Importa a interface JpaRepository para realizar operações no banco de dados
import org.springframework.data.jpa.repository.Query;  // Importa a anotação Query para consultas personalizadas em JPQL

// 'PacienteRepository' é o repositório responsável por acessar e manipular os dados dos pacientes no banco de dados
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    // Método para verificar se o paciente está ativo, dado seu ID
    @Query("""
            select p.ativo
            from Paciente p
            where
            p.id = :id
            """)  // Consulta JPQL personalizada que retorna o campo 'ativo' do paciente com o ID fornecido
    boolean findAtivoById(Long id);  // Retorna true se o paciente estiver ativo, false caso contrário

    // Método para verificar se já existe um paciente com o mesmo email ou CPF
    boolean existsByEmailOrCpf(String email, String cpf);  // Retorna true se um paciente com o mesmo email ou CPF já existir no banco de dados

}
