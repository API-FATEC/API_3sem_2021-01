<template>
  <div>
    <v-col>
      <v-row justify="center" class="pa-2">
        <div id="titulo">
          <h1 class="display-1">Fechar Revisão</h1>
        </div>
      </v-row>
      <v-row class="pa-4">
        <v-col>
          <v-card class="pa-2" tile outlined color="white">
            <v-card-text>
              <v-container fluid>
                <v-row>
                  <v-col>
                    <h2>Selecione o documento com revisão aberta</h2>
                    <v-row class="mb-6" no-gutters>
                      <v-col>
                        <v-form ref="form" v-model="valid" lazy-validation>
                          <v-autocomplete
                              v-model="name"
                              :items="findedDocsNames"
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

                          <v-btn :disabled="!valid" color="primary" class="mr-4" @click="getDocument()" id="botao-enviar">Pesquisar</v-btn>
                          <v-btn color="error" class="mr-4" @click="reset" id="botao-limpar">Limpar</v-btn>
                        </v-form>
                      </v-col>
                    </v-row>
                  </v-col>
                  <v-col>
                    <v-row>
                      <v-row>
                        <h2>Todas as revisões</h2>
                        <v-data-table
                            :headers="headers"
                            :items="allReviews"
                            :items-per-page="5"
                            class="elevation-1"
                        >
                          <template v-slot:item.status="{ item }">
                            <v-chip :color="getOpenedColor(item.status)" dark>{{ item. status }}</v-chip>
                          </template>
                        </v-data-table>
                      </v-row>
                    </v-row>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col>
                    <div id="btn">
                      <v-btn depressed color="primary" @click="closeReview"> Fechar Revisão em aberto </v-btn>
                    </div>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </v-col>
  </div>
</template>

<script src="./close_review.js"></script>

<style scoped>

</style>