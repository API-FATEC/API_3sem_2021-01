<template>
    <v-form ref="form" lazy-validation>
      <v-autocomplete v-model="name" :items="findedDocs" filled label="Nome do documento" :search-input="findDocs"></v-autocomplete>
      <v-select v-model="partNumber" :items="findedPartNumbers" filled label="Part Number" ></v-select>

      <v-btn color="primary" class="mr-4" @click="searchCodelist" id="botao-enviar">Enviar</v-btn>
      <v-btn color="error" class="mr-4" id="botao-limpar">Limpar</v-btn>

      <v-btn color="primary" class="mr-4" id="botao-importar">Importar codelist</v-btn>
      <v-btn color="primary" class="mr-4" @click="enterEditMode" id="botao_editar" v-show="codelistFound">Editar</v-btn>
    </v-form>
</template>

<script src="./searchCodelist.js"></script>

<style scoped>

</style>