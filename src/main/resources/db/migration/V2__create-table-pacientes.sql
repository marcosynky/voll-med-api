CREATE TABLE pacientes (
    id BIGINT NOT NULL AUTO_INCREMENT,  -- Coluna 'id' como chave primária (auto incremento)
    nome VARCHAR(100) NOT NULL,  -- Coluna 'nome' para armazenar o nome do paciente, não pode ser nulo
    email VARCHAR(100) NOT NULL UNIQUE,  -- Coluna 'email' para armazenar o e-mail do paciente, não pode ser nulo e deve ser único
    cpf VARCHAR(14) NOT NULL UNIQUE,  -- Coluna 'cpf' para armazenar o CPF do paciente, não pode ser nulo e deve ser único
    telefone VARCHAR(20) NOT NULL,  -- Coluna 'telefone' para armazenar o telefone do paciente, não pode ser nulo
    logradouro VARCHAR(100) NOT NULL,  -- Coluna 'logradouro' para armazenar o nome da rua do endereço, não pode ser nulo
    bairro VARCHAR(100) NOT NULL,  -- Coluna 'bairro' para armazenar o bairro do endereço, não pode ser nulo
    cep VARCHAR(9) NOT NULL,  -- Coluna 'cep' para armazenar o código postal, não pode ser nulo
    complemento VARCHAR(100),  -- Coluna 'complemento' para armazenar dados adicionais do endereço (opcional)
    numero VARCHAR(20),  -- Coluna 'numero' para armazenar o número da residência (opcional)
    uf CHAR(2) NOT NULL,  -- Coluna 'uf' para armazenar o estado, com exatamente 2 caracteres, não pode ser nula
    cidade VARCHAR(100) NOT NULL,  -- Coluna 'cidade' para armazenar o nome da cidade, não pode ser nula
    ativo BOOLEAN NOT NULL,  -- Coluna 'ativo' para indicar se o paciente está ativo (verdadeiro ou falso), não pode ser nula

    PRIMARY KEY (id)  -- Define a coluna 'id' como a chave primária da tabela
);
