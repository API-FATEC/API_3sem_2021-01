import Vue from 'vue';

const eventBus = new Vue();

export default {
    name: "search-codelist",
    data: () => ({
        name: '',
        partNumber: '',
        traits: [],
        codelistFound: false,
        findedDocs: [],
        findedPartNumbers: [],
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

    created() {
        eventBus.$on('codelistFound', function (traits) {
            this.setCodelistFound();
            this.traits = traits;
        });
    },

    methods: {
        searchCodelist: function() {
            eventBus.$emit('seeCodelist', {
                name: this.name,
                partNumber: this.partNumber
            });
        },

        setCodelistFound: function() {
            this.codelistFound = true;
        },

        searchDocs: function() {

        },

        searchPartNumbers: function() {

        },

        enterEditMode: function() {
            eventBus.$emit('editCodelist', {
                name: this.name,
                partNumber: this.partNumber,
                traits: this.traits
            });
        }
    }
}