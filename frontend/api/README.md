# api

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).

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

### Tela de importação de documentos
![Tela de importação do codelist](https://user-images.githubusercontent.com/50988433/112736254-18ab1700-8f30-11eb-9cba-3f8e4d472026.png)
### O resultado dos documentos inseridos é mostrado, bem como a contagem de blocos que foram inseridos.
![resultado da importação](https://user-images.githubusercontent.com/50988433/112739901-86fed200-8f4e-11eb-8e2c-a20f50b7bb72.png)
### Tela Para realização de buscas
![tela de busca](https://user-images.githubusercontent.com/50988433/112739902-87976880-8f4e-11eb-9e14-384da115073f.png)
### A busca retorna todos os blocos para o documento pesquisado
![resultado da busca](https://user-images.githubusercontent.com/50988433/112739899-86663b80-8f4e-11eb-89e2-96e4a5d977e8.png)

O usuário poderá visualizar os seus documentos em uma estrutura de listas mais confortável e confiável.
