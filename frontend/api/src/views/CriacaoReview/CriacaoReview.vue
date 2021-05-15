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
                    <v-card class="mx-auto" max-width="500">
                      <v-list shaped>
                        <v-list-item-group v-model="model" multiple>
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
                    </v-card>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col>
                    <div id="btn">
                      <v-btn depressed color="primary"> Create Review </v-btn>
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

<script src="./CriacaoReview.js" ></script>

<style>
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