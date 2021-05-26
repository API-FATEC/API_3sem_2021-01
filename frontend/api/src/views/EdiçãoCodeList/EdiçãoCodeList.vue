<template>
  <div>
    <v-col>
      <v-row justify="center" class="pa-2">
        <div id="titulo">
          <h1 class="display-1">Edição CodeList</h1>
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
                        <v-form ref="form" v-model="valid" lazy-validation>
                          <v-text-field
                            v-model="name"
                            :counter="nameCounter"
                            :rules="nameRules"
                            label="Nome do documento"
                            required
                          ></v-text-field>

                          <v-text-field
                            v-model="partNumber"
                            :counter="partNumberCounter"
                            :rules="partNumberRuler"
                            label="Part Number"
                            required
                          ></v-text-field>

                          <v-btn
                            :disabled="!valid"
                            color="primary"
                            class="mr-4"
                            @click="sendFile"
                            id="botao-enviar"
                          >
                            Enviar
                          </v-btn>

                          <v-btn
                            color="error"
                            class="mr-4"
                            @click="reset"
                            id="botao-limpar"
                          >
                            Limpar Formulário
                          </v-btn>
                        </v-form>
                      </v-col>
                    </v-row>
                    <br />
                    <br />
                    <v-divider></v-divider>
                    <br />
                    <br />
                    <v-row>
                      <v-col>
                        <v-data-table
                          :headers="[...headers, ...teste]"
                          :items="desserts"
                          sort-by="calories"
                          class="elevation-1"
                        >
                          <template v-slot:top>
                            <v-toolbar flat>
                              <v-toolbar-title>CodeList</v-toolbar-title>
                              <v-divider
                                class="mx-4"
                                inset
                                vertical
                              ></v-divider>
                              <v-spacer></v-spacer>
                              <v-dialog v-model="dialog" max-width="500px">
                                <template v-slot:activator="{ on, attrs }">
                                  <v-btn
                                    color="primary"
                                    dark
                                    class="mb-2"
                                    v-bind="attrs"
                                    v-on="on"
                                  >
                                    Novo Bloco
                                  </v-btn>
                                  <v-spacer></v-spacer>
                                </template>
                                <!--pop up do botão Novo Bloco-->
                                <v-card>
                                  <v-card-title>
                                    <span class="headline">{{
                                      formTitle
                                    }}</span>
                                  </v-card-title>
                                  <v-card-text>
                                    <v-container>
                                      <v-row>
                                        <v-col
                                          cols="12"
                                          sm="6"
                                          md="4"
                                          v-for="propriedade in Object.keys(
                                            editedItem
                                          )"
                                          :key="propriedade"
                                        >
                                          <v-text-field
                                            v-model="editedItem[propriedade]"
                                            :label="propriedade"
                                          ></v-text-field>
                                        </v-col>
                                      </v-row>
                                    </v-container>
                                  </v-card-text>
                                  <v-card-actions>
                                    <v-spacer></v-spacer>
                                    <v-btn
                                      color="blue darken-1"
                                      text
                                      @click="close"
                                    >
                                      Cancelar
                                    </v-btn>
                                    <v-btn
                                      color="blue darken-1"
                                      text
                                      @click="save"
                                    >
                                      Salvar
                                    </v-btn>
                                  </v-card-actions>
                                </v-card>
                              </v-dialog>
                              <!--Dialog Novo Traço-->
                              <v-dialog
                                v-model="dialogNovoTraco"
                                max-width="400px"
                              >
                                <template v-slot:activator="{ on, attrs }">
                                  <v-row>
                                    <v-col></v-col>
                                    <v-col>
                                      <v-btn
                                        color="primary"
                                        dark
                                        class="mb-2"
                                        v-bind="attrs"
                                        v-on="on"
                                      >
                                        Novo Traço
                                      </v-btn>
                                    </v-col>
                                  </v-row>
                                  <v-spacer></v-spacer>
                                </template>
                                <v-card>
                                  <v-card-title>
                                    <span class="headline">{{
                                      formTitle1
                                    }}</span>
                                  </v-card-title>
                                  <v-card-text>
                                    <v-container>
                                      <v-row>
                                        <v-col cols="12" sm="6" md="12">
                                          <v-text-field
                                            v-model="editedTraco.nomeTraco"
                                            label="Nome Traço"
                                            :rules="tracoRules"
                                            required
                                          ></v-text-field>
                                        </v-col>
                                      </v-row>
                                    </v-container>
                                  </v-card-text>
                                  <v-card-actions>
                                    <v-spacer></v-spacer>
                                    <v-btn
                                      color="blue darken-1"
                                      text
                                      @click="closeTraco"
                                    >
                                      Cancelar
                                    </v-btn>
                                    <v-btn
                                      color="blue darken-1"
                                      text
                                      @click="novaColuna"
                                    >
                                      Salvar
                                    </v-btn>
                                  </v-card-actions>
                                </v-card>
                              </v-dialog>
                              <v-dialog
                                v-model="dialogDelete"
                                max-width="525px"
                              >
                                <v-card>
                                  <v-card-title class="headline"
                                    >Você tem certeza que deseja excluir o
                                    item?</v-card-title
                                  >
                                  <v-card-actions>
                                    <v-spacer></v-spacer>
                                    <v-btn
                                      color="blue darken-1"
                                      text
                                      @click="closeDelete"
                                      >Cancelar</v-btn
                                    >
                                    <v-btn
                                      color="blue darken-1"
                                      text
                                      @click="deleteItemConfirm"
                                      >Sim</v-btn
                                    >
                                    <v-spacer></v-spacer>
                                  </v-card-actions>
                                </v-card>
                              </v-dialog>
                            </v-toolbar>
                          </template>
                          <template v-slot:item.actions="{ item }">
                            <v-icon small class="mr-2" @click="editItem(item)">
                              mdi-pencil
                            </v-icon>
                            <v-icon small @click="deleteItem(item)">
                              mdi-delete
                            </v-icon>
                          </template>
                          <template v-slot:no-data>
                            <v-btn color="primary" @click="initialize">
                              Reset
                            </v-btn>
                          </template>
                        </v-data-table>
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

<script src="./EdiçãoCodeList.js" ></script>

<style>
#titulo {
  margin-top: 10px;
}
</style>