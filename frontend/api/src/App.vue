<template>
  <div id="app">
    <v-app>
      <v-main>
        <v-app-bar color="#006494" dark>
          <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>

          <v-img
            src="@/assets/Logo-teste.jpg"
            contain
            max-height="64px"
            max-width="64px"
          >
          </v-img>
          <v-toolbar-title class="black--text">MOM</v-toolbar-title>
        </v-app-bar>

        <v-navigation-drawer v-model="drawer" absolute temporary width="320px">
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
              feito com Vuejs.
            </span>
          </v-row>
        </v-footer>
      </v-main>
    </v-app>
  </div>
</template>

<script>

export default {
  name: 'App',

  components: {
  },

  data: () => ({
    drawer: false,
    menus: [
      {conteudo: "Home", url: "/", icon: "mdi-home-variant"},
      {conteudo: "Importação CodeList", url: "/ImportacaoCodeList", icon: "mdi-file"},
      {conteudo: "Busca", url: "/docsearch", icon: "mdi-search-web"},
    ]
  }),
};
</script>