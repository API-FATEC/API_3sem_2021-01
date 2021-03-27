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

#### Membros e papéis:
- Tobias Lino      -> Scrum Master, Java Developer
- Wallace Caetano  -> Product Owner, Java Developer
- Devanir Ramos    -> Java Developer
- Tairik Johnny    -> Frontend Developer
- Gabriel Timoteo  -> Frontend Developer

## Configuração do Ambiente
> **Pre-requisitos:**
- Java 11
- Maven 3
- Postgres
- Docker e Docker Compose
> **Preparação do Banco de dados**
- criar uma base de dados no postgres com o usuário `mom` e senha `mom`
  > Se utilizar o Postgres no Docker, subir o container do banco com o comando `docker-compose -f docker/docker-compose.yml up -d`
- Executar o comando `mvn liquibase:update` para construir os objetos do banco
> **Subindo o sistema**
- Executar o comando `mvn clean package spring-boot:run`
- A aplicação estará em execução no `locahost:9090`
> **Testando a aplicação**
- Executar o comando `mvn test`

## Organização de Sprints

### Sprint 1
##### O entregável
O sistema permite a importação de uma codelist existente (em formato Excel), garantindo que o usuário consiga continuar 
o seu trabalho da forma que é feita atualmente, sem grandes necessidades de mudanças ou ter o trabalho de inserir novos 
documentos manualmente no novo sistema.

A importação é feita fornecendo o nome do documento e o part number correspondente e inserindo o arquivo da codelist.
O sistema então irá tratar o arquivo, gerar os documentos e seus blocos correspondentes e salvá-los no banco de dados.

O usuário poderá visualizar os seus documentos em uma estrutura de listas mais confortável e confiável.

### Sprint 2
### Sprint 3
### Sprint 4
