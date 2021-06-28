<template>
  <div id="app">
    <v-app>
      <v-main>
        <v-app-bar color="#0266B1" dark>
          <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>
          <!--
          <v-img
            src="@/assets/logo-mom.png"
            contain
            max-height="64px"
            max-width="64px"
          >
          </v-img>
          -->
          <v-toolbar-title class="black--text">MOM</v-toolbar-title>
        </v-app-bar>

        <v-navigation-drawer v-model="drawer" absolute temporary width="250px">
          <v-list nav dense>
            <v-list-item>
              <v-list-item-title>Menu</v-list-item-title>
            </v-list-item>
            <v-list-item-group active-class="blue--text text--darken-1">
              <v-list-item
                v-for="(item, index) in menus"
                :key="index"
                :to="item.url"
              >
                <v-list-item-icon>
                  <v-icon>{{ item.icon }}</v-icon>
                </v-list-item-icon>
                <v-list-item-title>{{ item.conteudo }}</v-list-item-title>
              </v-list-item>
            </v-list-item-group>
          </v-list>
        </v-navigation-drawer>
        <router-view></router-view>

        <v-footer id="core-footer" absolute height="42">
          <v-row justify="center">
            <span class="font-weight-light copyright">
              &copy;
              {{ new Date().getFullYear() }}
              - MOM
            </span>
          </v-row>
        </v-footer>
      </v-main>
    </v-app>
  </div>
</template>

<script>
export default {
  name: "App",
  components: {},
  data: () => ({
    drawer: false,
    menus: [
      { conteudo: "Home", url: "/", icon: "mdi-home-variant" },
      {
        conteudo: "Importar CodeList",
        url: "/ImportacaoCodeList",
        icon: "mdi-file-upload",
      },
      {
        conteudo: "Editar CodeList",
        url: "/EdiçãoCodeList",
        icon: "mdi-file-edit",
      },
      {
        conteudo: "Buscar documento",
        url: "/docsearch",
        icon: "mdi-folder-search",
      },
      {
        conteudo: "Criar Revisão",
        url: "/CriacaoReview",
        icon: "mdi-folder-plus",
      },
      {
        conteudo: "Editar Blocos da Revisão",
        url: "/EditarBlocosRevisao",
        icon: "mdi-folder-edit",
      },
      {
        conteudo: "Fechar Revisão",
        url: "/closeReview",
        icon: "mdi-folder-remove",
      },
      {
        conteudo: "Baixar Documentação",
        url: "/BaixarDocumentacao",
        icon: "mdi-folder-download",
      },
      {
        conteudo: "Gerar LEP",
        url: "/GerarLEP",
        icon: "mdi-file-download",
      },
    ],
  }),
};
</script>