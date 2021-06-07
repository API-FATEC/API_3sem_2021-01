<template>
  <div>
    <v-col>
      <v-row justify="center" class="pa-2"> </v-row>
      <v-row justify="center" class="pa-2">
        <div>
          <h1 class="display-1">Upload do CodeList</h1>
        </div>
      </v-row>
      <v-row class="pa-4">
        <v-col>
          <v-card class="pa-6" color="#0266B1">
            <v-card class="pa-2" tile outlined color="white">
              <v-card-text>
                <v-row class="mb-6" no-gutters>
                  <v-col>
                    <v-file-input
                      v-model="files"
                      color="blue accent-4"
                      counter
                      label="Inserir o CodeList"
                      multiple
                      placeholder="Select your files"
                      prepend-icon="mdi-paperclip"
                      outlined
                      :show-size="1000"
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
                  </v-col>
                </v-row>
                <v-row>
                  <v-col>
                    <div id="btn">
                      <v-form ref="form" v-model="valid" lazy-validation>
                        <v-btn
                          :disabled="!valid"
                          color="primary"
                          class="mr-4"
                          @click="sendFile"
                          id="botao-enviar"
                        >
                          Enviar
                        </v-btn>
                      </v-form>
                    </div>
                  </v-col>
                </v-row>
              </v-card-text>
            </v-card>
          </v-card>
          <v-card>
            <v-simple-table>
              <thead>
                <tr>
                  <th class="text-left">Id</th>
                  <th class="text-left">Nome</th>
                  <th class="text-left">Part Number</th>
                  <th class="text-left">Traço</th>
                  <th class="text-left">Total de Blocos</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="item in response" :key="item.id">
                  <td>{{ item.id }}</td>
                  <td>{{ item.name }}</td>
                  <td>{{ item.partNumber }}</td>
                  <td>{{ item.trait }}</td>
                  <td>{{ item.blocks.length }}</td>
                </tr>
              </tbody>
            </v-simple-table>
          </v-card>
        </v-col>
      </v-row>
    </v-col>
  </div>
</template>

<script src="./ImportacaoCodeList.js" ></script>

<style>
#botao-enviar {
  margin-top: 20px;
}
#botao-limpar {
  margin-top: 20px;
}
.file-wrapper input {
  position: absolute;
  top: 0;
  right: 0;
  cursor: pointer;
  opacity: 0;
  filter: alpha(opacity=0);
  font-size: 300px;
  height: 200px;
}
#btn {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
</style>
