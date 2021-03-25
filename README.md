# Projeto API 3º semestre Banco de Dados.

Projeto realizado em parceria com a FATEC de São José dos Campos, alunos do curso de Banco de Dados e com a EMBRAER.

#### Cliente
**Time de Publicação Operacional**, da EMBRAER, responsáveis por emitir, aprovar e revisar os manuais operacionais de aviação, destinados a pilotos, tripulação, despatcher de aeronaves e provedores de treinamento.

##### Produto do Cliente
Manuais operacionais de aviação, que incluem limitações, procedimentos, informações sobre performance, itens inoperantes, checklists, sistemas de aeronaves, etc.

Os manuais são documentos compostos por duas partes: PN (Part number, sigla em inglês) e Traço, que é um conjunto de blocos que dividem o documento.

Os documentos também passam por revisões, esta é uma característica deste tipo de manual: disponibilizar/identificar a versão do documento revisado.

## Objetivo
Desenvolver um sistema que permita customizar, controlar e revisar documentos formados por fragmentos armazenados em rquivos PDF, usando regras específicas para gerar o documento final.

# Produto final
[ link do site de produção ]

#### Membros e papéis:
- Tobias Lino      -> Scrum Master
- Wallace Caetano  -> Product Owner
- Ruan César       -> Developer
- Jonatas Ferreira -> Developer
- Devanir Ramos    -> Developer
- Tairik Johnny    -> Developer
- Gabriel Timoteo  -> Developer

### Observações: 
#### As atas de reunião, localização de bugs, planejamento e tarefas serão registradas na aba Issues.
#### Informações sobre tecnologias (framework, versão, banco de dados, servidor de aplicação, backend, frontend, etc.) serão registradas na aba Wiki.

## Configuração do Ambiente
> **Pre-requisitos:**
- Java 11
- Maven 3
- Oracle XE 11g ou superior
- Docker e Docker Compose
> **Preparação do Banco de dados**
- criar uma base de dados no Oracle com o usuário `mom` e senha `mom`
  > Se utilizar o Oracle no Docker, subir o container do banco com o comando `docker-compose -f docker/docker-compose.yml up -d`
- Executar o comando `mvn liquibase:update -Plocal` para construir os objetos do banco
> **Subindo o sistema**
- Executar o comando `mvn clean package spring-boot:run`
- A aplicação estará em execução no `locahost:9090`
> **Testando a aplicação**
- Executar o comando `mvn test`

## Organização de Sprints

### Sprint 1
Conhecer as necessidades do cliente, definição dos requisitos (User stories) e estudar qual seria o produto mínimo viável.

### Sprint 2
Focar em estimativas, treinamento, critérios de qualidade e na modelagem do banco.

### Sprint 3
Começar a entregar as implementações parciais do sistema.

### Sprint 4
Aperfeiçoar o produto, determinando novas versões, novas funcionalidades, etc.
