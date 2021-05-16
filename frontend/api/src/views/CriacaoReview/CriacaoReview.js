import { http } from "../../services/config";
import { DocumentsEndpoints } from "../../model/endpoints/EndpointsMapping";

export default {
    data: () => ({
        // Formulario
        valid: true,
        name: '',
        partNumber: '',
        findedPartNumbers: [],
        findedDocs: [],
        findedDocsNames: [],

        items: [],
        selectedBlocks: [],

        document: [],
        openedReview: [],
        hasOpenedReview: false,
    }),

    created() {
        this.initialize();
    },

    computed: {
        findDocs: function () {
            this.searchDocs();
        },

        canCreate() {
            return this.selectedBlocks.length === 0;
        },
    },

    watch: {
        name: function (documentName) {
            this.searchPartNumbers(documentName);
        }
    },

    methods: {
        initialize() {},

        reset() {
            this.$refs.form.reset()
            this.dialogError = false;
            this.items = [];
        },

        searchPartNumbers(documentName) {
            http.get(DocumentsEndpoints.FIND_PART_NUMBER_BY_NAME, {
                params: {
                    document_name: documentName
                }
            }).then(response => {
                this.findedPartNumbers = response.data;
            }).catch(error => {
                console.log(error)
            });
        },

        searchDocs() {
            http.get(DocumentsEndpoints.FIND_ALL_DOCS)
                .then(response => {
                    this.findedDocs = response.data;
                    let docsNames = [];
                    response.data.forEach(doc => docsNames.push(doc.name));

                    this.findedDocsNames = docsNames;
                }).catch(error => {
                    console.log(error)
                });
        },

        getBlocks() {
            let blocks = this.document.blocks;
            this.items = this.getBlocksNames(blocks);
        },

        getBlocksNames(blocks) {
            let blocksNames = [];
            blocks.forEach(block => {
                blocksNames.push(`${block.name} - ${block.code}`);
            });
            return blocksNames;
        },

        getDocumentBlocks() {
            http.get(DocumentsEndpoints.FIND_ALL_BY, {
                params: {
                    document_name: this.name,
                    part_number: this.partNumber
                }
            })
                .then(response => {
                    this.document = response.data;
                    this.getBlocks();
                }).catch(error => {
                console.log(error)
            });

            this.hasOpenedReviews();
        },

        createReview() {
           console.log(this.selectedBlocks);

           let blocks = [];
           this.selectedBlocks.forEach(block => {
               let name = block.substring(0, block.indexOf(' - '));
               let code = block.substring(block.indexOf(' - ') + 3, block.length);

               let blocksInDocument = this.searchBlocksInDocument(name, code);
               if (blocksInDocument.length > 0 && blocksInDocument.length < 2) {
                    blocks.push(blocksInDocument[0]);
               }
           });

           console.log(blocks);
        },

        searchBlocksInDocument(name, code) {
            return this.document.blocks.filter(block => block.name === name && block.code === parseInt(code));
        },

        hasOpenedReviews() {
            http.get('/revision/opened', {
                params: {
                    id: this.document.id
                }
            })
                .then(response => {
                    this.openedReview = response.data;
                }).catch(error => {
                console.log(error)
            });

            this.hasOpenedReview = this.this.openedReview.length > 0;
        },
    },
}
