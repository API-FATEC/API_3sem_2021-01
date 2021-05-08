<template>
  <div>
    <v-col>
      <v-row justify="center" class="pa-2">
        <div id="titulo">
          <h1 class="display-1">Criação de Review</h1>
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
                    <v-data-table
                      dense
                      :headers="[...headers, ...teste]"
                      :items="desserts"
                      sort-by="calories"
                      class="elevation-1"
                      :items-per-page="50"
                    >
                      <template v-slot:top>
                        <v-toolbar flat>
                          <v-toolbar-title>CodeList</v-toolbar-title>
                          <v-divider class="mx-4" inset vertical></v-divider>
                          <v-spacer></v-spacer>
                        </v-toolbar>
                      </template>

                      <template v-slot:item.actions="{ item }">
                        <v-simple-checkbox
                          v-model="item.actions"
                          color="blue"
                        ></v-simple-checkbox>
                      </template>
                    </v-data-table>
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