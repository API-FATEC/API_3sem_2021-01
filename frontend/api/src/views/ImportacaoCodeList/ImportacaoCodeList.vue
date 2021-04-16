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
                  <v-col>
                    <v-file-input
                      label="Inserir o CodeList"
                      outlined
                      dense
                      v-model="file"
                    ></v-file-input>
                    <div id="app" @dragover.prevent @drop.prevent>
                      <div class="container" @drop="handleFileDrop">
                        Add your files here:
                        <br />
                        <div class="file-wrapper">
                          <input
                            type="file"
                            name="file-input"
                            multiple="True"
                            @change="handleFileInput"
                          />
                          Click or drag to insert.
                        </div>
                        <ul>
                          <li v-for="(file, index) in files" :key="(file, index)">
                            {{ file.name }} ({{ file.size }} b)
                            <button @click="removeFile(index)" title="Remove">
                              X
                            </button>
                          </li>
                        </ul>
                      </div>
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

.file-wrapper {
  text-align: center;
  width: 200px;
  height: 3em;
  vertical-align: middle;
  display: table-cell;
  position: relative;
  overflow: hidden;
  background: gray; /* and other things to make it pretty */
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
</style>
