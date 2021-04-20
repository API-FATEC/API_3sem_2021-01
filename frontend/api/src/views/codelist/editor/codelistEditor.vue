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

const eventBus = new Vue();

export default {
  name: "codelistEditor",

  data: () => ({
    name: '',
    partNumber: '',
    desserts: [],
    headers: [],
  }),

  created() {
    eventBus.$on("editCodelist", function ({name, partNumber}) {
      this.name = name;
      this.partNumber = partNumber;
    });
  }
}
</script>

<style scoped>

</style>