<template>
  <div>
    <v-col>
      <v-row justify="center" class="pa-2">
        <div id="titulo">
          <h1 class="display-1">Gerar LEP</h1>
        </div>
      </v-row>
      <v-row class="pa-4">
        <v-col>
          <v-card class="pa-6" color="#0266B1">
            <v-card class="pa-2" tile outlined color="white">
              <v-card-text>
                <v-row class="mb-6" no-gutters>
                  <v-col>
                    <v-row>
                      <v-col>
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
                              <v-text-field
                                v-model="trait"
                                placeholder="Traço"
                              ></v-text-field>

                              <v-btn
                                :disabled="!valid"
                                color="primary"
                                class="mr-4"
                                @click="getDocument()"
                                id="botao-enviar"
                                >Pesquisar</v-btn
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
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col>
                        <v-row justify="center">
                          <v-row justify="center" class="pa-2">
                            <div id="titulo">
                              <h2>Historico de revisões</h2>
                            </div>
                          </v-row>
                        </v-row>
                      </v-col>
                    </v-row>
                    <v-row id="list">
                      <v-col>
                        <v-row justify="center">
                          <v-data-table
                            :headers="headers"
                            :items="allReviews"
                            :items-per-page="5"
                            class="elevation-1"
                          >
                            <template v-slot:item.status="{ item }">
                              <v-chip
                                :color="getOpenedColor(item.status)"
                                dark
                                >{{ item.status }}</v-chip
                              >
                            </template>
                          </v-data-table>
                        </v-row>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col>
                        <div id="btn">
                          <div>
                            <v-btn
                              depressed
                              color="primary"
                              :disabled="canCreate"
                              @click="gerarLEP(trait)"
                            >
                              Gerar LEP
                            </v-btn>
                          </div>
                          <div>
                            <div>
                              <v-btn
                                depressed
                                color="primary"
                                @click="baixarFull(trait)"
                                id="btn-full"
                              >
                                Gerar FULL
                              </v-btn>
                            </div>
                          </div>
                            <div>
                              <div>
                                <v-btn
                                  depressed
                                  color="primary"
                                  @click="downloadDocs()"
                                  id="btn-full"
                                >
                                  Baixar FULL
                                </v-btn>
                              </div>
                            </div>
                        </div>
                      </v-col>
                    </v-row>
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

<script src="./GerarLEP.js"></script>

<style>
#titulo {
  margin-top: 10px;
}
#btn {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
}
#list {
  margin-top: 50px;
}
#btn-full{
  margin-left: 30px;
}
</style>