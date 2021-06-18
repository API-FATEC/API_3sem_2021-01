import { http } from "../../services/config";
import { DocumentsEndpoints } from "../../model/endpoints/EndpointsMapping";
import swal from 'sweetalert';

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
        hasOpenedReview: true,
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
        },
    },

    methods: {
        initialize() {},

        reset() {
            this.$refs.form.reset()
            this.dialogError = false;
            this.items = [];
            this.selectedBlocks = [];
            this.hasOpenedReview = false;
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
                    let documentId = response.data.id;

                    this.getBlocks();
                    this.hasOpenedReviews(documentId);
                }).catch(error => {
                console.log(error)
            });

        },

        createReview() {
            if (this.hasOpenedReview) {
                //alert('Já existe uma revisão aberta, feche-a antes!');
                swal("Aviso!", "Já existe uma revisão aberta, feche-a antes!", "warning");
                return;
            }
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

           let review = {
               createdDate: new Date(),
               document: this.document,
               status: 'OPENED',
               blocksInRevision: blocks
           };
           console.log(review);
           http.post('/revision/save', review)
               .then(response => {
                   console.log(response);
                   const revisionName = response.data.name;
                   //alert(`${revisionName} criada com Sucesso!`);
                   swal("Sucesso!", `${revisionName} criada com Sucesso!`, "success");
               })
               .catch(error => {
                   console.error(error);
               });
        },

        searchBlocksInDocument(name, code) {
            return this.document.blocks.filter(block => block.name === name && block.code === parseInt(code));
        },

        hasOpenedReviews(documentId) {
            http.get(`/revision/opened?document_id=${documentId}`)
                .then(response => {
                    let openedReview = response.data;
                    if (openedReview.name !== undefined) {
                        this.openedReview = response.data;
                        this.hasOpenedReview = true;
                        //alert(`A revisão ${this.openedReview.name} já está aberta! É preciso finaliza-la antes de prosseguir`);
                        swal("Aviso!", `Já existe uma revisão aberta para esse documento, feche a revisão ${this.openedReview.name} para continuar!`, "warning");
                    } else {
                        this.hasOpenedReview = false;
                    }
                }).catch(error => {
                console.log(error)
            });

        },
    },
}
