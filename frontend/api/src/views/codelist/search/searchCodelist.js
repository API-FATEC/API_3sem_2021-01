import {http} from "../../../services/config";
import {DocumentService} from "../../../scripts/domain/document/DocumentService";
import {HttpRequester} from "../../../scripts/domain/http/HttpRequester";
import {eventBus} from "../codelistPage.js";

export default {
    name: "search-codelist",
    data: () => ({
        name: '',
        partNumber: '',
        traits: [],
        codelistFound: false,
        findedDocs: [],
        findedPartNumbers: [],

        documentService: new DocumentService(new HttpRequester(http)),
    }),

    computed: {
        findDocs: function() {
            this.searchDocs();
        }
    },

    watch: {
        name: function (docName) {
            this.searchPartNumbers(docName);
        }
    },

    mounted() {
        eventBus.$on('codelistFound', function (traits) {
            this.setCodelistFound();
            this.traits = traits;
        });
    },

    methods: {
        searchCodelist: function() {
            console.log({
                name: this.name,
                partNumber: this.partNumber
            });

            eventBus.$emit('seeCodelist', {
                name: this.name,
                partNumber: this.partNumber
            });
        },

        setCodelistFound: function() {
            this.codelistFound = true;
        },

        searchDocs: function() {
            this.documentService.getDocNames()
                .then(response => {
                    this.findedDocs = response.data;
                }).catch(error => {
                    console.log(error);
            });
        },

        searchPartNumbers: function() {
            this.documentService.getPartNumbers(this.name)
                .then(response => {
                    this.findedPartNumbers = response.data;
                }).catch(erro => {
                    console.log(erro);
            });
        },

        enterEditMode: function() {
            eventBus.$emit('editCodelist', {
                name: this.name,
                partNumber: this.partNumber,
                traits: this.traits
            });
        },
    }
}