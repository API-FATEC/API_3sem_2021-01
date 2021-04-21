<template>
  <v-data-table
      dense
      :headers="headers"
      :items="desserts"
      sort-by="calories"
      class="elevation-1"
      :items-per-page="30"
  >
    <template v-slot:top>
      <v-toolbar flat>
        <v-toolbar-title>CodeList</v-toolbar-title>
        <v-divider class="mx-4" inset vertical></v-divider>
      </v-toolbar>
    </template>
  </v-data-table>
</template>

<script>
import Vue from 'vue';
import {http} from "../../../services/config";
import {CodelistService} from "../../../scripts/domain/codelist/CodelistService";
import {HttpRequester} from "../../../scripts/domain/http/HttpRequester";

const eventBus = new Vue();

export default {
  name: "codelistEditor",

  data: () => ({
    name: '',
    partNumber: '',
    desserts: [],
    headers: [],
    traits: [],

    codelistService: new CodelistService(new HttpRequester(http)),
  }),

  created() {
    eventBus.$on("editCodelist", function ({name, partNumber}) {
      this.name = name;
      this.partNumber = partNumber;
    });
  },

  methods: {
    saveCodelist: function() {
      this.codelistService.saveCodelist(this.desserts, {
        name: this.name,
        partNumber: this.partNumber,
        traits: this.traits
      });
    }
  }
}
</script>

<style scoped>

</style>