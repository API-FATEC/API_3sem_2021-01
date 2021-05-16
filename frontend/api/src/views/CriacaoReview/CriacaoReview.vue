<template>
  <div>
    <v-col>
      <v-row justify="center" class="pa-2">
        <div id="titulo">
          <h1 class="display-1">Criação de Revisão</h1>
        </div>
      </v-row>
      <v-row class="pa-4">
        <v-col>
          <v-card class="pa-2" tile outlined color="white">
            <v-card-text>
              <v-container fluid>
                <v-row>
                  <v-col>
                    <h2>Selecione o documento a ser revisado</h2>
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

                          <v-btn :disabled="!valid" color="primary" class="mr-4" @click="getDocumentBlocks()" id="botao-enviar">Pesquisar</v-btn>
                          <v-btn color="error" class="mr-4" @click="reset" id="botao-limpar">Limpar</v-btn>
                        </v-form>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-alert :value="hasOpenedReview" border="left" color="indigo" dark>Já existe uma review aberta</v-alert>
                    </v-row>
                  </v-col>
                  <v-col>
                    <v-row>
                      <v-row>
                        <h2>Selecione os blocos a serem revisados</h2>
                        <v-list  class="overflow-auto" style="max-height: 400px; alignment: left">
                          <v-list-item-group v-model="selectedBlocks" multiple>
                            <template v-for="(item, i) in items">
                              <v-divider
                                v-if="!item"
                                :key="`divider-${i}`"
                              ></v-divider>

                              <v-list-item
                                v-else
                                :key="`item-${i}`"
                                :value="item"
                                active-class="blue--text text--accent-4"
                              >
                                <template v-slot:default="{ active }">
                                  <v-list-item-content>
                                    <v-list-item-title
                                      v-text="item"
                                    ></v-list-item-title>
                                  </v-list-item-content>

                                  <v-list-item-action>
                                    <v-checkbox
                                      :input-value="active"
                                      color="blue accent-4"
                                    ></v-checkbox>
                                  </v-list-item-action>
                                </template>
                              </v-list-item>
                            </template>
                          </v-list-item-group>
                        </v-list>
                      </v-row>
                    </v-row>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col>
                    <div id="btn">
                      <v-btn depressed color="primary" :disabled="canCreate" @click="createReview"> Create Review </v-btn>
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

<script src="./CriacaoReview.js" ></script>

<style>
.search-card {
  alignment: left;
  max-height: max-content;
}

.block-list {
  max-height: 400px;
  alignment: left;
}

#titulo {
  margin-top: 10px;
}
#btn {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
</style>