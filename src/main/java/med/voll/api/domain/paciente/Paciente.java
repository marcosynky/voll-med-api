package med.voll.api.domain.paciente;

import jakarta.persistence.*;  // Importa as anotações de JPA para mapeamento de entidades
import lombok.EqualsAndHashCode;  // Para gerar os métodos equals e hashCode baseados no 'id'
import lombok.Getter;  // Para gerar automaticamente os métodos getters para os campos
import lombok.NoArgsConstructor;  // Para gerar automaticamente o construtor sem parâmetros
import med.voll.api.domain.endereco.Endereco;  // Importa a classe Endereco
import med.voll.api.domain.paciente.dto.DadosAtualizacaoPaciente;  // Importa o DTO de atualização
import med.voll.api.domain.paciente.dto.DadosCadastroPaciente;  // Importa o DTO de cadastro

// A classe 'Paciente' é uma entidade JPA representando a tabela 'pacientes' no banco de dados
@Table(name = "pacientes")  // Especifica o nome da tabela no banco de dados
@Entity(name = "Paciente")  // Define a classe como uma entidade JPA
@Getter  // Gera automaticamente os métodos getter para os campos da classe
@NoArgsConstructor  // Gera um construtor sem parâmetros (necessário para o JPA)
@EqualsAndHashCode(of = "id")  // Gera os métodos equals e hashCode com base no campo 'id'
public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)  // Define o campo 'id' como chave primária e auto-incrementável
    private Long id;  // ID do paciente

    private String nome;  // Nome completo do paciente

    private String email;  // E-mail do paciente

    private String telefone;  // Telefone do paciente

    private String cpf;  // CPF do paciente

    @Embedded  // Indica que o campo 'endereco' é um tipo embutido (embeddable)
    private Endereco endereco;  // Endereço do paciente (completo, utilizando a classe 'Endereco')

    private Boolean ativo;  // Define se o paciente está ativo (pode ser desativado)

    // Construtor que cria um objeto 'Paciente' a partir dos dados do DTO 'DadosCadastroPaciente'
    public Paciente(DadosCadastroPaciente dados) {
        this.ativo = true;  // Define o paciente como ativo por padrão ao ser criado
        this.nome = dados.nome();  // Atribui o nome do DTO ao campo 'nome'
        this.email = dados.email();  // Atribui o email do DTO ao campo 'email'
        this.telefone = dados.telefone();  // Atribui o telefone do DTO ao campo 'telefone'
        this.cpf = dados.cpf();  // Atribui o CPF do DTO ao campo 'cpf'
        this.endereco = new Endereco(dados.endereco());  // Atribui o endereço do DTO ao campo 'endereco'
    }

    // Método para atualizar as informações do paciente com base no DTO 'DadosAtualizacaoPaciente'
    public void atualizarInformacoes(DadosAtualizacaoPaciente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();  // Atualiza o nome se não for nulo
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();  // Atualiza o telefone se não for nulo
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());  // Atualiza o endereço se não for nulo
        }
    }

    // Método para desativar o paciente
    public void desativar() {
        this.ativo = false;  // Define o paciente como inativo
    }

}
