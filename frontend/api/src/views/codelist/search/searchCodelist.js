import Vue from 'vue';

const eventBus = new Vue();

export default {
    name: "search-codelist",
    data: () => ({
        name: '',
        partNumber: '',
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
        eventBus.$on('codelistFound', function () {
            this.setCodelistFound();
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
                partNumber: this.partNumber
            });
        }
    }
}