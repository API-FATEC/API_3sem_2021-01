<template>
  <div>
    <v-col>
      <v-row justify="center" class="pa-2"> </v-row>
      <v-row justify="center" class="pa-2">
        <div>
          <titulo class="display-1">Upload do CodeList</titulo>
        </div>
      </v-row>
      <v-row class="pa-4">
        <v-col>
          <v-card class="pa-6" color="#006494">
            <v-card class="pa-2" tile outlined color="white">
              <v-card-text>
                <v-row class="mb-6" no-gutters>
                  <v-col>
                    <v-form ref="form" v-model="valid" lazy-validation>
                      <v-text-field
                        v-model="name"
                        :counter="3"
                        :rules="nameRules"
                        label="Nome do documento"
                        required
                      ></v-text-field>

                      <v-text-field
                        v-model="partNumber"
                        :counter="4"
                        :rules="partNumberRuler"
                        label="Part Number"
                        required
                      ></v-text-field>

                      <v-btn
                        :disabled="!valid"
                        color="primary"
                        class="mr-4"
                        @click="validate"
                        id="botao-enviar"
                      >
                        Enviar
                      </v-btn>

                      <v-btn color="error" class="mr-4" @click="reset" id="botao-limpar">
                        Limpar Formulário
                      </v-btn>
                    </v-form>
                  </v-col>
                  <v-col>
                    <v-file-input
                      label="Inserir o CodeList"
                      outlined
                      dense
                    ></v-file-input>
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

<script src="./ImportacaoCodeList.js" ></script>

<style>
#botao-enviar{
    margin-top: 20px;
}
#botao-limpar{
    margin-top: 20px;
}
</style>
