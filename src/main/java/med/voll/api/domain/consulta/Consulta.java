package med.voll.api.domain.consulta;

import jakarta.persistence.*;  // Importações para as anotações JPA, que são usadas para mapear a classe para o banco de dados
import lombok.EqualsAndHashCode;  // Anotação do Lombok para gerar os métodos equals() e hashCode()
import lombok.Getter;  // Anotação do Lombok para gerar os getters automaticamente
import lombok.NoArgsConstructor;  // Anotação do Lombok para gerar o construtor sem argumentos
import med.voll.api.domain.medico.Medico;  // Importação do modelo de Medico
import med.voll.api.domain.paciente.Paciente;  // Importação do modelo de Paciente

import java.time.LocalDateTime;  // Classe usada para representar a data e hora de uma consulta

// A anotação @Table define o nome da tabela no banco de dados
@Table(name = "consultas")
// A anotação @Entity define que esta classe será uma entidade JPA, ou seja, será mapeada para uma tabela no banco de dados
@Entity(name = "Consulta")
@Getter  // Lombok vai gerar automaticamente os getters para todos os campos
@NoArgsConstructor  // Lombok vai gerar o construtor sem argumentos (necessário para o JPA)
@EqualsAndHashCode(of = "id")  // Lombok vai gerar os métodos equals() e hashCode() usando o campo "id"
public class Consulta {

    // Define o campo "id" como a chave primária da tabela
    @Id
    // Define que o valor do "id" será gerado automaticamente com a estratégia de incremento do banco de dados
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Define um relacionamento ManyToOne entre Consulta e Medico
    // Muitos registros de consulta podem ter o mesmo médico
    @ManyToOne(fetch = FetchType.LAZY)  // Lazy loading significa que os dados do médico serão carregados apenas quando necessários
    @JoinColumn(name = "medico_id")  // A coluna "medico_id" na tabela de consultas vai referenciar a chave primária da tabela de médicos
    private Medico medico;

    // Define um relacionamento ManyToOne entre Consulta e Paciente
    // Muitos registros de consulta podem ter o mesmo paciente
    @ManyToOne(fetch = FetchType.LAZY)  // Lazy loading para o paciente
    @JoinColumn(name = "paciente_id")  // A coluna "paciente_id" na tabela de consultas vai referenciar a chave primária da tabela de pacientes
    private Paciente paciente;

    // Campo que armazena a data e hora da consulta
    private LocalDateTime data;

    // Construtor com parâmetros, utilizado para criar uma nova consulta
    public Consulta(Medico medico, Paciente paciente, LocalDateTime data) {
        this.medico = medico;
        this.paciente = paciente;
        this.data = data;
    }

}
