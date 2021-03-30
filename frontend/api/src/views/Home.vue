<template>
  <div>
    <v-col>
      <v-row justify="center" class="pa-2">
        <div id="logo">
          <img alt="MOM logo" src="../assets/Logo-teste.jpg" />
        </div>
      </v-row>
      <v-row justify="center" class="pa-2">
        <div>
          <titulo class="display-1" id="titulo">MOM - Management of Operation Manuals</titulo>
        </div>
      </v-row>
      <v-row class="pa-4">
        <v-col>
          <v-card class="pa-6" color="#006494">
            <v-card class="pa-2" tile outlined color="white">
              <v-card-text>
                <v-row class="mb-6" no-gutters>
                  <v-col>
                    <div id="texto-home">
                      <p>Nosso sitema se chama MOM e seu principal objetivo é automatizar os processos do cliente.</p>
                      <p>O sistema conta com um tela de upload de documentos aonde o usuario ira poder fazer upload da planilha CodeList.</p>
                      <p>O sistema tem uma tela de geração de documento aonde ele ira inserir as informações necessarias para gerar o documento final.</p>
                    </div>
                  </v-col>
                </v-row>
              </v-card-text>
            </v-card>
          </v-card>
        </v-col>
      </v-row>
    </v-col>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: "Home",
  components: {},
};
</script>

<style>
#texto-home {
  color: black;
  font-family: Arial, Helvetica, sans-serif;
  font-size: 20px;
}
</style>