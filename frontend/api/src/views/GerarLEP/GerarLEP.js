import {http} from "../../services/config";
import {DocumentsEndpoints} from "../../model/endpoints/EndpointsMapping";
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
        trait: '',

        headers: [{
            text: 'Name',
            align: 'start',
            value: 'name'
        },
            { text: 'Status', value: 'status'},
            { text: 'Data de criação', value: 'createdDate'},
            { text: 'Última atualização', value: 'lastUpdateDate'}],
        items: [],
        allReviews: [],

        document: [],
        openedReview: [],
        closedReview: [],
    }),

    created() {
        this.initialize();
    },

    computed: {
        findDocs: function () {
            this.searchDocs();
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
            this.allReviews = [];
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

        getDocument() {
            http.get(DocumentsEndpoints.FIND_ALL_BY, {
                params: {
                    document_name: this.name,
                    part_number: this.partNumber
                }
            })
                .then(response => {
                    this.document = response.data;
                    let documentId = response.data.id;
                    this.allReviews = this.document.revisions;
                    this.getOpenedReview(documentId);

                    console.log(this.openedReview);
                    console.log(this.allReviews);
                }).catch(error => {
                console.log(error);
                //alert('Não foi possível obter o documento')
                swal("Erro!", "Não foi possível obter o documento", "error");
            });
        },

        getOpenedReview(documentId) {
            http.get(`/revision/opened?document_id=${documentId}`)
                .then(response => {
                    this.openedReview = response.data;
                    console.log(response.data);
                    if (this.openedReview.name === undefined) {
                        //alert('Não existe nenhuma revisão aberta')
                        swal("Aviso!", "Não existe nenhuma revisão aberta para este documento", "warning");
                    }
                }).catch(error => {
                console.log(error)
                //alert('Não foi possível obter a revisão')
                swal("Erro!", "Não foi possível obter a revisão", "error");
            });
        },

        getOpenedColor(status) {
            return status === 'OPENED' ? 'orange' : 'green';
        },

        baixarFull:(trait)=>{
            return http.get(`/document/download/full?trait=${trait}`)
        },

        gerarLEP:(trait)=>{
            return http.get(`/lep/download?trait=${trait}`)
        },

        downloadDocs() {
                    http.get(DocumentsEndpoints.DOWNLOADDOC, {responseType: "arraybuffer"})
                        .then(response => {
                            let blob = new Blob([response.data], { type: 'application/pdf' })
                            let link = document.createElement('a')
                            link.href = window.URL.createObjectURL(blob)
                            link.download = 'ABC-1234-60-FULL.pdf'
                            link.click()
                        })
        },
    },
}
