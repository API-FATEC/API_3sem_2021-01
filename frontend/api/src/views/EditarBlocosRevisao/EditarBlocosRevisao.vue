<template>
  <div>
    <v-col>
      <v-row justify="center" class="pa-2">
        <div id="titulo">
          <h1 class="display-1">Editar os blocos da revisão</h1>
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

                              <v-btn
                                :disabled="!valid"
                                color="primary"
                                class="mr-4"
                                @click="
                                  getDocument();
                                  getDocumentBlocks();
                                "
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
                    <v-row id="list">
                      <v-col>
                        <v-row justify="center">
                          <h2>Blocos do documento</h2>
                          <v-list
                            class="overflow-auto"
                            style="
                              max-height: 400px;
                              width: 300px;
                              alignment: left;
                              margin-top: 20px;
                            "
                          >
                            <v-list-item-group
                              v-model="selectedBlocks"
                            >
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
                      </v-col>
                      <v-col>
                        <v-row justify="center">
                          <h2>Historico de Revisões</h2>
                          <v-data-table
                            :headers="headers"
                            :items="allReviews"
                            :items-per-page="5"
                            class="elevation-1"
                            style="
                              max-height: 500px;
                              width: 800px;
                              margin-top: 20px;
                            "
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
                        <v-row justify="center">
                          <v-file-input
                            v-model="files"
                            color="blue accent-4"
                            counter
                            label="Inserir o(s) documento(s)"
                            multiple
                            placeholder="Select your files"
                            prepend-icon="mdi-paperclip"
                            outlined
                            :show-size="1000"
                            style="margin-top: 40px"
                          >
                            <template v-slot:selection="{ index, text }">
                              <v-chip
                                v-if="index < 2"
                                color="blue accent-4"
                                dark
                                label
                                small
                              >
                                {{ text }}
                              </v-chip>

                              <span
                                v-else-if="index === 2"
                                class="overline grey--text text--darken-3 mx-2"
                              >
                                +{{ files.length - 2 }} File(s)
                              </span>
                            </template>
                          </v-file-input>
                        </v-row>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col>
                        <div id="btn">
                          <v-btn depressed color="primary" @click="closeReview">
                            Importar documentos
                          </v-btn>
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

<script src="./EditarBlocosRevisao.js"></script>

<style>
#list {
  margin-top: 50px;
}
#btn {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
}
</style>