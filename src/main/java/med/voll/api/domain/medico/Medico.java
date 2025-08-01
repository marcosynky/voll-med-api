package med.voll.api.domain.medico;

import jakarta.persistence.*;  // Anotações JPA para mapeamento de entidades
import lombok.EqualsAndHashCode;  // Lombok para gerar equals e hashCode baseados no ID
import lombok.Getter;  // Lombok para gerar os getters automaticamente
import lombok.NoArgsConstructor;  // Lombok para gerar o construtor sem parâmetros
import med.voll.api.domain.endereco.Endereco;  // Endereço, um componente embutido (Embeddable)
import med.voll.api.domain.medico.dto.DadosAtualizacaoMedico;  // DTO para atualização de dados do médico
import med.voll.api.domain.medico.dto.DadosCadastroMedico;  // DTO para cadastro de médico

@Table(name = "medicos")  // Define o nome da tabela no banco de dados
@Entity(name = "Medico")  // Define a entidade JPA e o nome da classe no banco
@Getter  // Lombok gera os getters automaticamente para todos os campos
@NoArgsConstructor  // Lombok gera o construtor sem argumentos
@EqualsAndHashCode(of = "id")  // Lombok gera os métodos equals e hashCode baseados no ID
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)  // Define o ID e a estratégia de geração
    private Long id;  // ID do médico
    private String nome;  // Nome do médico
    private String email;  // E-mail do médico
    private String telefone;  // Telefone do médico
    private String crm;  // CRM do médico (registro profissional)

    @Enumerated(EnumType.STRING)  // Define que a especialidade será armazenada como string no banco
    private Especialidade especialidade;  // Especialidade do médico

    @Embedded  // Define que Endereco é um componente embutido (Embeddable)
    private Endereco endereco;  // Endereço do médico

    private Boolean ativo;  // Flag que indica se o médico está ativo ou não

    // Construtor que inicializa o médico com os dados de cadastro
    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;  // Inicializa o médico como ativo por padrão
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());  // Cria o endereço a partir do DTO
    }

    // Método que atualiza as informações do médico com os dados fornecidos
    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());  // Atualiza o endereço se fornecido
        }
    }

    // Método para desativar o médico
    public void desativar() {
        this.ativo = false;  // Marca o médico como inativo
    }
}
