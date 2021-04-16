import { http } from "../../services/config";
// import { Block } from "../../scripts/domain/Block"
import {CodelistEndpoints, DocumentsEndpoints} from "../../model/endpoints/EndpointsMapping";
// import { Document } from "../../scripts/domain/Document";
import {CodelistFactory} from "../../scripts/domain/Codelist";

export default {
    data: () => ({
        // Formulario
        valid: true,
        name: '',
        nameCounter: 30,
        partNumberCounter: 10,
        nameRules: [
            v => !!v || 'O Nome do documento é obrigatório!',
            v => (v && v.length <= 30) || 'O Nome deve possuir no máximo 30 caracteres!',
        ],
        partNumber: '',
        partNumberRuler: [
            v => !!v || 'O Part Number é obrigatório!',
            v => (v && v.length <= 10) || 'O Part Number deve possuir no máximo 10 caracteres!',
            //v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
        ],
        // Tabela
        dialog: false,
        dialogDelete: false,
        dialogNovoTraco: false,
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
        teste: [{ text: 'Actions', value: 'actions', sortable: false }],
        desserts: [],
        traco: [],
        editedIndex: -1,
        editedItem: {
            numero_secao: '',
            numero_sub_secao: '',
            numero_block: '',
            block_name: '',
            code: '',
            remarks: '',
        },
        defaultItem: {
            numero_secao: '',
            numero_sub_secao: '',
            numero_block: '',
            block_name: '',
            code: '',
            remarks: '',
        },
        editedTraco: {
            nomeTraco: '',
            valorDefault: '',
        },
        tracoRules: [
            v => !!v || 'O Nome do traço é obrigatório!',
            v => (v && v.length <= 30) || 'O Nome deve possuir no máximo 30 caracteres!',
        ],
        findedPartNumbers: [],
        findedDocs: [],
        allDocumentsResponse: [],
        allBlocksResponse: []
    }),

    created() {
        this.initialize();
    },

    computed: {
        formTitle() {
            return this.editedIndex === -1 ? 'Novo Bloco' : 'Editar Bloco'
        },
        formTitle1() {
            return this.editedIndex === -1 ? 'Novo Traço' : 'Editar Traço'
        },
        findDocs: function () {
            this.searchDocs();
        }
    },

    watch: {
        dialog(val) {
            val || this.close()
        },
        dialogTraco(val) {
            val || this.close()
        },
        dialogDelete(val) {
            val || this.closeDelete()
        },
        name: function (docName) {
            this.searchPartNumbers(docName);
        }
    },

    methods: {
        // Formulario
        validate() {
            this.$refs.form.validate()
        },

        reset() {
            this.$refs.form.reset()
        },

        initialize() {
            this.desserts = [];
        },

        // Tabela
        novaColuna(){
            if(this.editedTraco.nomeTraco.length === 0){
                //this.closeTraco()
                return
            }
            var nomeValue = this.editedTraco.nomeTraco.valueOf()
            nomeValue = nomeValue.toLowerCase()
            nomeValue = nomeValue.replaceAll(' ', '_')
            this.headers.push({text: this.editedTraco.nomeTraco, value: nomeValue})
            this.desserts=this.desserts.map(item => {
                var objeto = {}
                objeto[nomeValue] = 0
                return {...item,...objeto}
            })
            this.closeTraco()
        },

        editItem(item) {
            this.editedIndex = this.desserts.indexOf(item)
            this.editedItem = Object.assign({}, item)
            this.dialog = true
        },

        deleteItem(item) {
            this.editedIndex = this.desserts.indexOf(item)
            this.editedItem = Object.assign({}, item)
            this.dialogDelete = true
        },

        deleteItemConfirm() {
            this.desserts.splice(this.editedIndex, 1)
            this.closeDelete()
        },

        close() {
            this.dialog = false
            this.$nextTick(() => {
                this.editedItem = Object.assign({}, this.defaultItem)
                this.editedIndex = -1
            })
        },

        closeTraco() {
            this.dialogNovoTraco = false
            this.$nextTick(() => {
                this.editedTraco.nomeTraco = ''
                this.editedTraco.valorDefault = ''
            })
        },

        closeDelete() {
            this.dialogDelete = false
            this.$nextTick(() => {
                this.editedItem = Object.assign({}, this.defaultItem)
                this.editedIndex = -1
            })
        },

        save() {
            if (this.editedIndex > -1) {
                Object.assign(this.desserts[this.editedIndex], this.editedItem)
            } else {
                this.desserts.push(this.editedItem)
            }
            this.close()
        },

        getCodelist(){
            this.validate();

            http.get(CodelistEndpoints.FIND_BY, {
                params: {
                    document_name: this.name,
                    part_number: this.partNumber
                }
            }).then(response => {
                const codelist = CodelistFactory.createFromResponse(response.data);
                console.log(codelist);


                let columnsToAdd = [];
                for (let i = 0; i < codelist.documents.length; ++i) {
                    let value = codelist.documents[i].trait
                    this.headers.push({text: value, value: value});
                    columnsToAdd.push(value);
                }

                let codelistBlocks = [];
                codelist.codelistBlocks.forEach(function (codelistBlock) {
                    let objeto = {...codelistBlock.block};
                    for (let i = 0; i < columnsToAdd.length; ++i) {
                        objeto[columnsToAdd[i]] = codelistBlock.checklist[i];
                    }
                    codelistBlocks.push(objeto);
                });
                console.log(codelistBlocks);

                this.desserts = codelistBlocks;
            }).catch(error => console.log(error));
        },

        searchPartNumbers(documentName) {
            http.get(DocumentsEndpoints.FIND_PART_NUMBER_BY_NAME, {
                params: {
                    document_name: documentName
                }
            }).then(response => {
                this.findedPartNumbers = response.data;
            }).catch(error => console.log(error));
        },

        searchDocs() {
            http.get(DocumentsEndpoints.FIND_ALL_DOCS)
                .then(response => {
                    this.findedDocs = response.data;
                }).catch(error => console.log(error));
        },
    },
}
