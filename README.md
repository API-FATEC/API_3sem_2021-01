# MOM - Management of Operational Manuals.
![Maven Central](https://img.shields.io/maven-central/v/org.apache.maven/apache-maven)
![](https://img.shields.io/badge/java-jdk11-green)
![](https://img.shields.io/badge/postgres-13-blue)
![](https://img.shields.io/badge/spring--boot-2.4.3-brightgreen)
![](https://img.shields.io/badge/nodejs-14.16-darkgreen)
## Projeto API 3º semestre Banco de Dados.

Projeto realizado em parceria com a FATEC de São José dos Campos, alunos do curso de Banco de Dados e uma empresa da Indústria Aeronáutica.

#### Cliente
**Time de Publicação Operacional**, da indústria aeronáutica, responsáveis por emitir, aprovar e revisar os manuais operacionais de aviação, destinados a pilotos, tripulação, despatcher de aeronaves e provedores de treinamento.

##### Produto do Cliente
Manuais operacionais de aviação, que incluem limitações, procedimentos, informações sobre performance, itens inoperantes, checklists, sistemas de aeronaves, etc.

Os manuais são documentos compostos por duas partes: PN (Part Number, sigla em inglês) e Traço, que é um conjunto de blocos que dividem o documento.

Os documentos também passam por revisões, esta é uma característica deste tipo de manual: disponibilizar/identificar a versão do documento revisado.

## Objetivo
Desenvolver um sistema que permita customizar, controlar e revisar documentos formados por fragmentos armazenados em arquivos PDF, usando regras específicas para gerar o documento final.

#### Membros e papéis:
- Tairik Johnny    -> Scrum Master, Frontend Developer -> [linkedin.com/in/tairik-nishimura/](https://linkedin.com/in/tairik-nishimura/)
- Wallace Caetano  -> Product Owner, Java Developer -> [linkedin.com/in/wallace-caetano/](https://linkedin.com/in/wallace-caetano/)
- Devanir Ramos    -> Java Developer -> [linkedin.com/in/devanir-ramos-junior/](https://linkedin.com/in/devanir-ramos-junior/)


# Organização de Sprints
### O planejamento e o progresso durante as Sprints do projeto poderá ser visto em [Entregas](https://github.com/API-FATEC/API_3sem_2021-01/wiki/Entregas).

## Configuração do Ambiente
### **Pre-requisitos:**
- Java 11
- Maven 3 
- Oracle
- Docker e Docker Compose
- NodeJs versão 14.16
### **Preparação do Banco de dados**
- criar uma base de dados no Oracle com nome `mom` e criar o usuário `mom` e senha `mom`
- Executar o comando `mvn liquibase:update` para construir os objetos do banco
## **Subindo o backend**

### **Especificar a pasta de upload de documentos**
- Abrir o arquivo `src/main/resources/application.properties`
- Definir a variável `defaultupload-path` para a pasta desejada

> **Obs: é necessário especificar o caminho completo para a pasta**
- Executar o comando `mvn clean package spring-boot:run`
- A aplicação estará em execução no `localhost:9090`token>.
- A url com o resultado será mostrada no console do Maven (possívelmente `localhost:9000`)
## **Subindo o frontend**
- Entrar na pasta /frontend
- Executar o comando `npm install` para instalar as dependências.
- Executar o comando `npm run serve` para rodar o sistema
- Poderá ser acessado no endereço `localhost:5500`

### [Configurando um banco de dados diferente](https://github.com/API-FATEC/API_3sem_2021-01/wiki/Configurando-outro-banco-de-dados)

### [Conteúdo adicional](https://github.com/API-FATEC/API_3sem_2021-01/wiki/Conte%C3%BAdo-Adicional)
