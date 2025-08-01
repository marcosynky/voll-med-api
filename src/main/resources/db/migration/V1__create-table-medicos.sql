CREATE TABLE medicos (
    id BIGINT NOT NULL AUTO_INCREMENT,  -- Coluna 'id' como chave primária (auto incremento)
    nome VARCHAR(100) NOT NULL,  -- Coluna 'nome' para armazenar o nome do médico, não pode ser nulo
    email VARCHAR(100) NOT NULL UNIQUE,  -- Coluna 'email' para armazenar o e-mail do médico, não pode ser nulo e deve ser único
    crm VARCHAR(6) NOT NULL UNIQUE,  -- Coluna 'crm' para armazenar o CRM do médico, não pode ser nulo e deve ser único
    especialidade VARCHAR(100) NOT NULL,  -- Coluna 'especialidade' para armazenar a especialidade do médico, não pode ser nula
    telefone VARCHAR(20) NOT NULL,  -- Coluna 'telefone' para armazenar o telefone do médico, não pode ser nulo
    logradouro VARCHAR(100) NOT NULL,  -- Coluna 'logradouro' para armazenar o nome da rua do endereço, não pode ser nulo
    bairro VARCHAR(100) NOT NULL,  -- Coluna 'bairro' para armazenar o bairro do endereço, não pode ser nulo
    cep VARCHAR(9) NOT NULL,  -- Coluna 'cep' para armazenar o código postal, não pode ser nulo
    complemento VARCHAR(100),  -- Coluna 'complemento' para armazenar dados adicionais do endereço (opcional)
    numero VARCHAR(20),  -- Coluna 'numero' para armazenar o número da residência (opcional)
    uf CHAR(2) NOT NULL,  -- Coluna 'uf' para armazenar o estado, com exatamente 2 caracteres, não pode ser nula
    cidade VARCHAR(100) NOT NULL,  -- Coluna 'cidade' para armazenar o nome da cidade, não pode ser nula
    ativo BOOLEAN NOT NULL,  -- Coluna 'ativo' para indicar se o médico está ativo (verdadeiro ou falso), não pode ser nula

    PRIMARY KEY (id)  -- Define a coluna 'id' como a chave primária da tabela
);
