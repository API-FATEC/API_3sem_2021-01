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
                    <search-codelist></search-codelist>
                    <v-divider></v-divider>
                    <v-row>
                      <v-col>
                        <codelist-visualizer></codelist-visualizer>
                        <codelist-editor></codelist-editor>
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

<script>
import searchCodelist from "./search/searchCodelist.vue";
import codelistVisualizer from "./visualizer/codelistVisualizer.vue";
import codelistEditor from "./editor/codelistEditor.vue";

export default {
  name: "codelistPage",

  components: {
    searchCodelist,
    codelistVisualizer,
    codelistEditor
  }
}
</script>

<style scoped>

</style>