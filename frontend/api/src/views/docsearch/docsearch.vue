<template>
    <div>
        <v-col>
            <v-row justify="center" class="pa-2"></v-row>
            <v-row justify="center" class="pa-2">
                <div>
                    <h1 class="display-1">Busca Documentos</h1>
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
                                            <v-text-field v-model="name" placeholder="Documento"></v-text-field>
                                            <v-text-field v-model="partNumber" placeholder="Part Number"></v-text-field>
                                            <v-text-field v-model="trait" placeholder="Traço"></v-text-field>
                                            <v-btn color="primary" @click="searchDoc" id="botao-buscar">Buscar</v-btn>
                                        </v-form>
                                        <br>
                                        <v-card>
                                          <v-simple-table>
                                            <thead>
                                                <tr>
                                                    <th class="text-left">Seção</th>
                                                    <th class="text-left">Subseção</th>
                                                    <th class="text-left">Bloco</th>
                                                    <th class="text-left">Nome do bloco</th>
                                                    <th class="text-left">Código</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr
                                                    v-for="item in response"
                                                    :key="item.order"
                                                >
                                                    <td>{{ item.section }}</td>
                                                    <td>{{ item.subSection }}</td>
                                                    <td>{{ item.number }}</td>
                                                    <td>{{ item.name }}</td>
                                                    <td>{{ item.code }}</td>
                                                </tr>
                                            </tbody>
                                          </v-simple-table>
                                        </v-card>
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


<script src="./docsearch.js" ></script>