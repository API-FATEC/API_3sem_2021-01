<template>
  <div>
    <v-col>
      <v-row justify="center" class="pa-2">
        <div id="logo">
          <img alt="MOM logo" src="../assets/logo-mom.png" />
        </div>
      </v-row>
      <v-row justify="center" class="pa-2">
        <div>
          <h1 class="display-1" id="titulo">MOM - Management of Operation Manuals</h1>
        </div>
      </v-row>
      <v-row class="pa-4">
        <v-col>
          <v-card class="pa-6" color="#0266B1">
            <v-card class="pa-2" tile outlined color="white">
              <v-card-text>
                <v-row class="mb-6" no-gutters>
                  <v-col>
                    <div id="texto-home">
                      <p>Nosso sitema se chama MOM - Management of Operation Manuals e seu principal objetivo é automatizar os processos de geração de documentos.</p>
                      <p>O sistema conta com um tela de upload de documentos aonde o usuário irá poder fazer upload da planilha CodeList e também uma tela de busca de documentos.</p>
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
#logo{
  margin-top: 10px;
}
</style>