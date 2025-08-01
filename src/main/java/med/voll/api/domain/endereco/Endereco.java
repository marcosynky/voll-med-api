package med.voll.api.domain.endereco;

import jakarta.persistence.Embeddable;  // Anotação que marca a classe como um componente embutido (embeddable)
import lombok.Getter;  // Anotação do Lombok para gerar automaticamente os getters
import lombok.NoArgsConstructor;  // Anotação do Lombok para gerar o construtor sem argumentos
import med.voll.api.domain.endereco.dto.DadosEndereco;  // DTO contendo os dados de endereço

// A anotação @Embeddable indica que a classe pode ser embutida em outra entidade no JPA
@Embeddable
@Getter  // Lombok gera automaticamente os getters para todos os campos da classe
@NoArgsConstructor  // Lombok gera o construtor padrão (sem parâmetros)
public class Endereco {

    // Definição dos campos do endereço
    private String logradouro;  // Logradouro (Rua, Avenida, etc.)
    private String bairro;  // Bairro
    private String cep;  // CEP
    private String numero;  // Número da residência
    private String complemento;  // Complemento, caso haja
    private String cidade;  // Cidade
    private String uf;  // Estado (UF - Unidade Federativa)

    // Construtor que recebe um objeto DadosEndereco para inicializar o Endereco
    public Endereco(DadosEndereco dados) {
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.uf = dados.uf();
        this.cidade = dados.cidade();
        this.numero = dados.numero();
        this.complemento = dados.complemeto();
    }

    // Método que atualiza as informações do endereço
    public void atualizarInformacoes(DadosEndereco dados) {
        // Verifica se o valor de cada campo é não-nulo antes de atualizar
        if (dados.logradouro() != null) {
            this.logradouro = dados.logradouro();
        }
        if (dados.bairro() != null) {
            this.bairro = dados.bairro();
        }
        if (dados.cep() != null) {
            this.cep = dados.cep();
        }
        if (dados.uf() != null) {
            this.uf = dados.uf();
        }
        if (dados.cidade() != null) {
            this.cidade = dados.cidade();
        }
        if (dados.numero() != null) {
            this.numero = dados.numero();
        }
        if (dados.complemeto() != null) {
            this.complemento = dados.complemeto();
        }
    }
}
