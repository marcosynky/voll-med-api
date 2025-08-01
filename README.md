# Voll.med - API Rest

## 📖 Sobre

Voll.med é uma API Rest para gestão de consultas médicas, desenvolvida utilizando **Java 17**, **Spring Boot 3**, **Maven** e **MySQL**. O projeto tem como objetivo facilitar o gerenciamento de médicos, pacientes e consultas, oferecendo funcionalidades de cadastro e agendamento de consultas. O projeto foi desenvolvido com foco na eficiência, escalabilidade e facilidade de integração.

## 🚀 Tecnologias

<div>
  <img src="https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=java&logoColor=white">
  <img src="https://img.shields.io/badge/Spring_Boot-3.0.6-green?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/Maven-3.8.1-blue?style=for-the-badge&logo=apachemaven&logoColor=white">
  <img src="https://img.shields.io/badge/MySQL-5.7-blue?style=for-the-badge&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/Hibernate-5.4.30.Final-red?style=for-the-badge&logo=hibernate&logoColor=white">
  <img src="https://img.shields.io/badge/Flyway-7.10.0-blue?style=for-the-badge&logo=flyway&logoColor=white">
  <img src="https://img.shields.io/badge/Lombok-1.18.20-green?style=for-the-badge&logo=lombok&logoColor=white">
</div>

<p>Este projeto utiliza as seguintes tecnologias:</p>
<ul>
  <li><strong>Java 17</strong>: Linguagem de programação utilizada para desenvolver a API.</li>
  <li><strong>Spring Boot 3</strong>: Framework Java utilizado para criar a API RESTful.</li>
  <li><strong>Maven</strong>: Ferramenta de automação de builds usada para gerenciar dependências e empacotar o projeto.</li>
  <li><strong>MySQL</strong>: Sistema de gerenciamento de banco de dados relacional usado para armazenar dados de médicos, pacientes e consultas.</li>
  <li><strong>Hibernate</strong>: Framework ORM utilizado para mapear as entidades Java para o banco de dados.</li>
  <li><strong>Flyway</strong>: Ferramenta de migração de banco de dados usada para gerenciar versões do banco de dados.</li>
  <li><strong>Lombok</strong>: Biblioteca utilizada para reduzir a verbosidade do código com anotações para getters, setters e construtores.</li>
</ul>

## 📊 Estado do Projeto

![Progresso](https://img.shields.io/badge/Progresso-70%25-red?style=for-the-badge&labelColor=000000&color=FF0000&logo=github)

> Atualmente, cerca de 70% das funcionalidades estão implementadas.

## 🧑‍💻 Autor

<h2>Marco Antônio</h2>

<p>Desenvolvedor Full-Stack</p>

<p>
  <a href="https://github.com/marcosynky" target="_blank">
    <img src="https://img.shields.io/badge/GitHub-000000?style=for-the-badge&logo=github&logoColor=white" />
  </a>
  <a href="https://www.linkedin.com/in/marco-antônio-developer-fullstack" target="_blank">
    <img src="https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white" />
  </a>
</p>

## 📱 Funcionalidades

- **Cadastro de médicos**: Permite o cadastro de médicos com nome, especialidade e CRM.
- **Cadastro de pacientes**: Permite o cadastro de pacientes com nome, CPF e data de nascimento.
- **Agendamento de consultas**: Permite agendar consultas associando um médico a um paciente, com data e horário.
- **Cancelamento de consultas**: Permite o cancelamento de consultas previamente agendadas.

## 🛠️ Como Rodar o Projeto

### Pré-requisitos

1. **Java 17**: Verifique se o Java 17 está instalado corretamente no seu sistema.
    - Instalação do Java: [Java 17 Docs](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

2. **MySQL**: Instale o MySQL e configure o banco de dados local.
    - Instalação do MySQL: [MySQL Docs](https://dev.mysql.com/doc/)

3. **Maven**: O Maven é utilizado para gerenciar as dependências do projeto.
    - Instalação do Maven: [Maven Docs](https://maven.apache.org/install.html)

### Passos para rodar o projeto

1. Clone o repositório para sua máquina local:

```bash
git clone https://github.com/marcosynky/voll-med-api.git
