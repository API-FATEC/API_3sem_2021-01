# MOM - Management of Operational Manuals.
![Maven Central](https://img.shields.io/maven-central/v/org.apache.maven/apache-maven)
![](https://img.shields.io/badge/java-jdk11-green)
![](https://img.shields.io/badge/postgres-13-blue)
![](https://img.shields.io/badge/spring--boot-2.4.3-brightgreen)
![](https://img.shields.io/badge/nodejs-14.16-darkgreen)
## Projeto API 3º semestre Banco de Dados.

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
### **Pre-requisitos:**
- Java 11
- Maven 3 
- Postgres
- Docker e Docker Compose
- NodeJs versão 14.16
### **Preparação do Banco de dados**
- criar uma base de dados no postgres com nome `mom` e criar o usuário `mom` e senha `mom`
  > Se utilizar o Postgres no Docker, subir o container do banco com o comando `docker-compose -f docker/docker-compose.yml up -d`
- Executar o comando `mvn liquibase:update` para construir os objetos do banco
## **Subindo o backend**

### **Especificar a pasta de upload de documentos**
- Abrir o arquivo `src/main/resources/application.properties`
- Definir a variável `defaultupload-path` para a pasta desejada

> **Obs: é necessário especificar o caminho completo para a pasta**
- Executar o comando `mvn clean package spring-boot:run`
- A aplicação estará em execução no `locahost:9090`
### **Testando a aplicação**
- Executar o comando `mvn clean test jacoco:report` para gerar os arquivos de cobertura de testes.
- Rodar o servidor local do SonarQube (Estamos utilizando a versão community).
- Assim que o servidor do sonar estiver com status `up`, executar o comando `mvn sonar:sonar`
> Obs: será necessário adicionar o token de acesso do sonar no comando acima com `-Dsonar.login=<seu_token>.
- A url com o resultado será mostrada no console do Maven (possívelmente `localhost:9000`)
## **Subindo o frontend**
- Entrar na pasta /frontend
- Executar o comando `npm install` para instalar as dependências.
- Executar o comando `npm run serve` para rodar o sistema
- Poderá ser acessado no endereço `localhost:5500`

## Organização de Sprints

## Sprint 1
***
### **User stories**
**Eu como parte do time de publicação, quero uma lista de blocos por documento, porque não posso misturar documentos 
diferentes e nem posso ter duplicação de blocos nos manuais.**

**Eu como parte do time de publicação, preciso que os documentos estejam organizados de acordo com a codelist, 
pois a codelist é o guia sobre a estruturação dos meus documentos.**
### O entregável
O sistema permite a importação de uma codelist existente (em formato Excel), garantindo que o usuário consiga continuar 
o seu trabalho da forma que é feita atualmente, sem grandes necessidades de mudanças ou ter o trabalho de inserir novos 
documentos manualmente no novo sistema.

A importação é feita fornecendo o nome do documento e o part number correspondente e inserindo o arquivo da codelist.
O sistema então irá tratar o arquivo, gerar os documentos e seus blocos correspondentes e salvá-los no banco de dados.

O usuário poderá visualizar os seus documentos em uma estrutura de listas mais confortável e confiável.

### As telas podem ser visualizadas em: [frontend](https://github.com/API-FATEC/API_3sem_2021-01/tree/main/frontend/api)

## Sprint 2
***
### **User stories**
**Eu como parte do time de publicação, desejo visualizar um codelist de um documento específico como um todo, para que seja fácil gerenciar quais blocos compõem determinado traço.**

**Eu como parte do time de publicação, desejo editar um codelist de um determinado documento, para que seja possível modificar a estrutura de um documento salvo na aplicação.**

**Eu como responsável por um documento, desejo criar um codelist para um determinado documento dentro da aplicação, pois é muito mais eficaz manter o gerenciamento de codelists no sistema, desde sua criação.**
***
> Entrega de um sistema de visualização, edição e criação de codelists.
## Sprint 3
## Sprint 4
