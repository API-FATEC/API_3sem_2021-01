import {http} from "../../services/config";
// import { Block } from "../../scripts/domain/Block"
import {CodelistEndpoints, DocumentsEndpoints} from "../../model/endpoints/EndpointsMapping";
// import { Document } from "../../scripts/domain/Document";
import {CodelistFactory} from "../../scripts/domain/Codelist";
import {BlockFactory} from "../../scripts/domain/Block";
import {DocumentFactory, DocumentRequestBody} from "../../scripts/domain/Document";

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
        emailRuler: [
            v => !!v || 'O e-mail é obrigatório!',
            v => /.+@.+\..+/.test(v) || 'E-mail inválido',
        ],
        // Tabela
        editMode: false,
        dialog: false,
        dialogDelete: false,
        dialogNovoTraco: false,
        dialogSalvar: false,
        dialogCancelar: false,
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
        documents: [],
        traits: [],
        desserts: [],
        editedDesserts: [],
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
        allBlocksResponse: [],
        email: '',
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
        defaultHeaders: function () {
            this.headers = [
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
            ];
        },
        // Formulario
        validate() {
            this.$refs.form.validate()
        },

        reset() {
            this.$refs.form.reset()
            this.desserts = [];
            this.defaultHeaders();
        },

        resetTableToDefault: function () {
            this.defaultHeaders();
            this.desserts = [];
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
                Object.assign(this.editedDesserts[this.editedIndex], this.editedItem)
            } else {
                this.editedDesserts.push(this.editedItem)
            }
            this.editMode = true;
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
                this.resetTableToDefault();
                const codelist = CodelistFactory.createFromResponse(response.data);
                this.documents = codelist.documents;

                let columnsToAdd = [];
                let headersToAdd = [];
                for (let i = 0; i < codelist.documents.length; ++i) {
                    let value = codelist.documents[i].trait
                    headersToAdd.push({text: 'Traço: ' + value, value: 'trait_' + value});
                    columnsToAdd.push(value);
                    this.traits.push('trait_' + value);
                }

                let codelistBlocks = [];
                codelist.codelistBlocks.forEach(function (codelistBlock) {
                    let objeto = {...codelistBlock.block};
                    for (let i = 0; i < columnsToAdd.length; ++i) {
                        objeto['trait_' + columnsToAdd[i]] = codelistBlock.checklist[i].valueOf();
                    }
                    codelistBlocks.push(objeto);
                });

                this.desserts = this.sortedCodelistBlocks(codelistBlocks);
                this.editedDesserts = this.sortedCodelistBlocks(codelistBlocks);
                for (let i = 0; i < headersToAdd.length; ++i) {
                    this.headers.push(headersToAdd[i]);
                }
            }).catch(error => console.log(error));
        },

        sortedCodelistBlocks: function (array) {
            function compare(a, b) {
                if (a.order < b.order) return -1;
                if (a.order > b.order) return 1;
                return 0;
            }

            return array.sort(compare);
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

        saveCodelist: function () {
            let codelistToSave = this.editedDesserts;
            let documents = [];
            for (let i = 0; i < this.documents.length; ++i) {
                let doc = this.documents[i];
                documents.push(DocumentFactory.createWithEmptyBlocks(doc));
            }

            for (let i = 0; i < codelistToSave.length; ++i) {
                let codelistBlock = codelistToSave[i];
                let block = BlockFactory.createFromCodelist(codelistToSave[i]);

                let checklist = [];
                for (let j = 0; j < this.traits.length; ++j) {
                    let trait = this.traits[j].valueOf();
                    checklist.push(Number.parseInt(codelistBlock[trait]));
                }

                for (let x = 0; x < checklist.length; ++x) {
                    if (checklist[x] === 1) {
                        documents[x].addBlock(block);
                    }
                }
            }
            console.log(documents);

            http.put(DocumentsEndpoints.SAVE_ALL, DocumentRequestBody.createList(documents))
                .then(response => {
                    console.log(response);
                })
                .catch(error => console.log(error));
            this.editMode = false;
        },

        cancelEdit: function () {
            this.editedDesserts = this.desserts;
            this.editMode = false;
        },

        enterEditMode: function() {
            this.editMode = true;
        },

        redirectToImportPage: function () {
            this.$router.push('ImportacaoCodeList').then().catch(error => console.log(error));
        }
    },
}
