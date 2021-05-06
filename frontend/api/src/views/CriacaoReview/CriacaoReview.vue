<template>
  <div>
    <v-col>
      <v-row justify="center" class="pa-2">
        <div id="titulo">
          <titulo class="display-1">Criação de Review</titulo>
        </div>
      </v-row>
      <v-row class="pa-4">
        <v-col>
          <v-card class="pa-6" color="#0266B1">
            <v-card class="pa-2" tile outlined color="white">
              <v-card-text>
                <v-row class="mb-6" no-gutters>
                  <v-col>
                    <v-form ref="form" v-model="valid" lazy-validation>
                      <v-autocomplete
                        v-model="name"
                        :items="findedDocs"
                        filled
                        label="Nome do documento"
                        :search-input="findDocs"
                      ></v-autocomplete>
                      <v-select
                        v-model="partNumber"
                        :items="findedPartNumbers"
                        filled
                        label="Part Number"
                      ></v-select>

                      <v-btn
                        :disabled="!valid"
                        color="primary"
                        class="mr-4"
                        @click="getCodelist"
                        id="botao-enviar"
                        >Enviar</v-btn
                      >
                      <v-btn
                        color="error"
                        class="mr-4"
                        @click="reset"
                        id="botao-limpar"
                        >Limpar</v-btn
                      >
                    </v-form>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col>
                    <v-treeview
                      selectable
                      :items="items"
                      selected-color="blue"
                    ></v-treeview>
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

<script src="./CriacaoReview.js" ></script>

<style>
#titulo {
  margin-top: 10px;
}
</style>