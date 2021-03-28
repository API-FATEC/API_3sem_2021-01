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

## Sprint 1
### **User storie**
**Eu como parte do time de publicação, quero uma lista de blocos por documento, porque não posso misturar documentos 
diferentes e nem posso ter duplicação de blocos nos manuais.**
### O entregável
O sistema permite a importação de uma codelist existente (em formato Excel), garantindo que o usuário consiga continuar 
o seu trabalho da forma que é feita atualmente, sem grandes necessidades de mudanças ou ter o trabalho de inserir novos 
documentos manualmente no novo sistema.

A importação é feita fornecendo o nome do documento e o part number correspondente e inserindo o arquivo da codelist.
O sistema então irá tratar o arquivo, gerar os documentos e seus blocos correspondentes e salvá-los no banco de dados.

O usuário poderá visualizar os seus documentos em uma estrutura de listas mais confortável e confiável.

### Tela de importação de documentos
![Tela de importação do codelist](https://user-images.githubusercontent.com/50988433/112736254-18ab1700-8f30-11eb-9cba-3f8e4d472026.png)
### O resultado dos documentos inseridos é mostrado, bem como a contagem de blocos que foram inseridos.
![resultado da importação](https://user-images.githubusercontent.com/50988433/112739901-86fed200-8f4e-11eb-8e2c-a20f50b7bb72.png)
### Tela Para realização de buscas
![tela de busca](https://user-images.githubusercontent.com/50988433/112739902-87976880-8f4e-11eb-9e14-384da115073f.png)
### A busca retorna todos os blocos para o documento pesquisado
![resultado da busca](https://user-images.githubusercontent.com/50988433/112739899-86663b80-8f4e-11eb-89e2-96e4a5d977e8.png)

## Sprint 2
## Sprint 3
## Sprint 4
