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
import { CodelistService } from "../../../scripts/domain/codelist/CodelistService";
import { HttpRequester } from "../../../scripts/domain/http/HttpRequester";
import { http } from "../../../services/config";
import { CodelistTableGenerator } from "../../../scripts/domain/codelist/CodelistTableGenerator";
import { CodelistFactory } from "../../../scripts/domain/Codelist";
import {eventBus} from "../codelistPage.js";

export default ({
  name: "codelistVisualizer",

  data: () => ({
    headers: [
      {
        text: 'Nº SEÇÃO',
        align: 'start',
        sortable: false,
        value: 'section',
      },
      { text: 'Nº SUB SEÇÃO', value: 'subSection' },
      { text: 'Nº BLOCK', value: 'number' },
      { text: 'BLOCK NAME', value: 'name' },
      { text: 'CODE', value: 'code' }
    ],
    desserts: [],

    name: '',
    partNumber: '',

    defaultItem: {
      section: '',
      sub_section: '',
      number: '',
      name: '',
      code: '',
    },
    traits: [],

    codelistService: new CodelistService(new HttpRequester(http)),
    codelistTableGenerator: new CodelistTableGenerator(new CodelistFactory()),
  }),

  mounted() {
    eventBus.$on('seeCodelist', function({name, partNumber}) {
      this.name = name;
      this.partNumber = partNumber;
    });

    console.log({
      name: this.name,
      pn: this.pn
    });

    this.getCodelist();
    this.resolveDefaultTraits();
  },

  methods: {
    getCodelist: function() {
      const codelist = this.codelistService.getCodelist(this.name, this.partNumber)
          .then(response => {
            console.log(response.data);
            return response.data;
          }).catch(error => {
            console.log(error);
          });

      console.log(codelist);
      const codelistDescriptor = this.codelistTableGenerator.generateCodelistTable(codelist, this.defaultItem);

      this.headers = codelistDescriptor.headers;
      this.desserts = codelistDescriptor.desserts;
      this.defaultItem = codelistDescriptor.defaultItem;
      this.traits = codelistDescriptor.traits;
    },

    resolveDefaultTraits() {
      eventBus.$emit('codelistFound', this.traits);
    }
  }
})
</script>

<style scoped>

</style>