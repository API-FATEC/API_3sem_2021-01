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
                          <v-autocomplete v-model="name" :items="findedDocs" filled label="Nome do documento" :search-input="findDocs"></v-autocomplete>
                          <v-select v-model="partNumber" :items="findedPartNumbers" filled label="Part Number" ></v-select>

                          <v-btn :disabled="!valid" color="primary" class="mr-4" @click="getCodelist" id="botao-enviar">Enviar</v-btn>
                          <v-btn color="error" class="mr-4" @click="reset" id="botao-limpar">Limpar</v-btn>

                          <v-btn color="primary" class="mr-4" @click="enterEditMode" id="botao_editar">Editar</v-btn>
                          <v-btn color="primary" class="mr-4" @click="redirectToImportPage" id="botao-importar">Importar codelist</v-btn>
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
                            dense
                          :headers="[...headers, ...teste]"
                          :items="desserts"
                          sort-by="calories"
                          class="elevation-1"
                            :items-per-page="50"
                        >
                          <template v-slot:footer>
                            <v-dialog v-model="dialogCancelar" max-width="500px">
                              <template v-slot:activator="{ on, attrs }">
                                <v-btn color="error" dark class="mb-2" v-bind="attrs" v-on="on" v-show="editMode">Descartar</v-btn>
                              </template>
                              <!--pop up do botão Cancelar-->
                              <v-card>
                                <v-card-title>
                                    <span class="headline">Podem existir alterações não salvas.</span>
                                </v-card-title>
                                <v-card-text>
                                  <v-container>
                                    <v-row>
                                      <span>Deseja realmente cancelar todas as alterações?</span>
                                    </v-row>
                                  </v-container>
                                </v-card-text>
                                <v-card-actions>
                                  <v-spacer></v-spacer>
                                  <v-btn color="blue darken-1" text @click="closeDialogCancel">Voltar</v-btn>
                                  <v-btn color="blue darken-1" text @click="cancelEdit">Descartar</v-btn>
                                </v-card-actions>
                              </v-card>
                            </v-dialog>
                            <!--Dialog Salvar-->
                            <v-dialog v-model="dialogSalvar" max-width="400px">
                              <template v-slot:activator="{ on, attrs }">
                                <v-row>
                                  <v-col>
                                    <v-btn color="primary" dark class="mb-2" v-bind="attrs" v-on="on" v-show="editMode">Salvar</v-btn>
                                  </v-col>
                                </v-row>
                                <v-spacer></v-spacer>
                              </template>
                              <v-card>
                                <v-card-title><span class="headline">Tem certeza?</span></v-card-title>
                                <v-card-text>
                                  <v-container>
                                    <v-row><span>Deseja realmente salvar as alterações?</span></v-row>
                                  </v-container>
                                </v-card-text>
                                <v-card-actions>
                                  <v-spacer></v-spacer>
                                  <v-btn color="blue darken-1" text @click="closeSalvarDialog">Voltar</v-btn>
                                  <v-btn color="blue darken-1" text @click="saveCodelist">Salvar</v-btn>
                                </v-card-actions>
                              </v-card>
                            </v-dialog>

                            <!--Dialog Salvo-->
                            <v-dialog v-model="dialogSaved" max-width="400px">
                              <v-card>
                                <v-card-title><span class="headline">Salvo com Sucesso!</span></v-card-title>
                                <v-card-actions>
                                  <v-spacer></v-spacer>
                                  <v-btn color="blue darken-1" text @click="closeSavedDialog">Ok</v-btn>
                                </v-card-actions>
                              </v-card>
                            </v-dialog>

                            <!--Dialog Error-->
                            <v-dialog v-model="dialogError" max-width="400px">
                              <v-card color="red" dark>
                                <v-card-title><span class="headline">Ocorreu um erro inesperado.</span></v-card-title>
                                <v-card-actions>
                                  <v-spacer></v-spacer>
                                  <v-btn color="white" text @click="closeDialogError">Sair</v-btn>
                                </v-card-actions>
                              </v-card>
                            </v-dialog>
                          </template>
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
                                  <v-btn color="primary" dark class="mb-2" v-bind="attrs" v-on="on" v-show="editMode">Novo Bloco</v-btn>
                                  <v-spacer></v-spacer>
                                </template>
                                <!--pop up do botão Novo Bloco-->
                                <v-card>
                                  <v-card-title><span class="headline">{{ formTitle }}</span></v-card-title>
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
                              <v-dialog v-model="dialogNovoTraco" max-width="400px">
                                <template v-slot:activator="{ on, attrs }">
                                  <v-row>
                                    <v-col></v-col>
                                    <v-col><v-btn color="primary" dark class="mb-2" v-bind="attrs" v-on="on" v-show="editMode">Novo Traço</v-btn></v-col>
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
                          <template v-slot:item.actions="{ item }" v-show="editMode">
                            <v-icon small class="mr-2" @click="editItem(item)" v-show="editMode">mdi-pencil</v-icon>
                            <v-icon small @click="deleteItem(item)" v-show="editMode">mdi-delete</v-icon>
                          </template>
                          <template v-slot:no-data>
                            <v-btn color="primary" @click="initialize">Reset</v-btn>
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