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
                value: 'numero_secao',
            },
            { text: 'Nº SUB SEÇÃO', value: 'numero_sub_secao' },
            { text: 'Nº BLOCK', value: 'numero_block' },
            { text: 'BLOCK NAME', value: 'block_name' },
            { text: 'CODE', value: 'code' },
            { text: 'Remarks', value: 'remarks' }
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
    }),

    computed: {
        formTitle() {
            return this.editedIndex === -1 ? 'Novo Bloco' : 'Editar Bloco'
        },
        formTitle1() {
            return this.editedIndex === -1 ? 'Novo Traço' : 'Editar Traço'
        },
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
    },

    created() {
        this.initialize()
    },

    methods: {
        // Formulario
        validate() {
            this.$refs.form.validate()
        },

        reset() {
            this.$refs.form.reset()
        },

        // Tabela
        novaColuna() {
            if (this.editedTraco.nomeTraco.length === 0) {
                //this.closeTraco()
                return
            }
            var nomeValue = this.editedTraco.nomeTraco.valueOf()
            nomeValue = nomeValue.toLowerCase()
            nomeValue = nomeValue.replaceAll(' ', '_')
            this.headers.push({ text: this.editedTraco.nomeTraco, value: nomeValue })
            this.desserts = this.desserts.map(item => {
                var objeto = {}
                objeto[nomeValue] = 0
                return { ...item, ...objeto }
            })
            this.closeTraco()
        },
        initialize() {
            this.desserts = [
                {
                    numero_secao: '00',
                    numero_sub_secao: '',
                    numero_block: '00',
                    block_name: 'Letter',
                    code: '50',
                    remarks: '-50',
                },
                {
                    numero_secao: '00',
                    numero_sub_secao: '',
                    numero_block: '00',
                    block_name: 'Letter',
                    code: '55',
                    remarks: '-55',
                },
                {
                    numero_secao: '00',
                    numero_sub_secao: '',
                    numero_block: '00',
                    block_name: 'Letter',
                    code: '60',
                    remarks: '-60',
                },
                {
                    numero_secao: '00',
                    numero_sub_secao: '',
                    numero_block: '01',
                    block_name: 'Cover',
                    code: '01',
                    remarks: '-50',
                },
                {
                    numero_secao: '00',
                    numero_sub_secao: '',
                    numero_block: '01',
                    block_name: 'Cover',
                    code: '02',
                    remarks: '-55',
                },
                {
                    numero_secao: '00',
                    numero_sub_secao: '',
                    numero_block: '01',
                    block_name: 'Cover',
                    code: '03',
                    remarks: '-60',
                },
                {
                    numero_secao: '00',
                    numero_sub_secao: '',
                    numero_block: '02',
                    block_name: 'LEP',
                    code: '01',
                    remarks: '-50',
                },
                {
                    numero_secao: '00',
                    numero_sub_secao: '',
                    numero_block: '02',
                    block_name: 'LEP',
                    code: '02',
                    remarks: '-55',
                },
                {
                    numero_secao: '00',
                    numero_sub_secao: '',
                    numero_block: '02',
                    block_name: 'LEP',
                    code: '03',
                    remarks: '-60',
                },
                {
                    numero_secao: '00',
                    numero_sub_secao: '',
                    numero_block: '03',
                    block_name: 'TOC',
                    code: '01',
                    remarks: '-50,-60',
                },
                {
                    numero_secao: '00',
                    numero_sub_secao: '',
                    numero_block: '03',
                    block_name: 'TOC',
                    code: '02',
                    remarks: '-55',
                },
                {
                    numero_secao: '02',
                    numero_sub_secao: '',
                    numero_block: '04',
                    block_name: 'Introduction',
                    code: '01',
                    remarks: '-50',
                },
                {
                    numero_secao: '02',
                    numero_sub_secao: '',
                    numero_block: '04',
                    block_name: 'Introduction',
                    code: '02',
                    remarks: '-55',
                },
                {
                    numero_secao: '02',
                    numero_sub_secao: '',
                    numero_block: '04',
                    block_name: 'Introduction',
                    code: '03',
                    remarks: '-60',
                },
                {
                    numero_secao: '03',
                    numero_sub_secao: '01',
                    numero_block: '03',
                    block_name: 'Episódio 2',
                    code: '14',
                    remarks: '-50,-60',
                },
                {
                    numero_secao: '03',
                    numero_sub_secao: '01',
                    numero_block: '03',
                    block_name: 'Episódio 2',
                    code: '15',
                    remarks: '-55',
                },
                {
                    numero_secao: '04',
                    numero_sub_secao: '',
                    numero_block: '02',
                    block_name: 'Episódio 3',
                    code: '01',
                    remarks: '-60',
                },
                {
                    numero_secao: '04',
                    numero_sub_secao: '',
                    numero_block: '02',
                    block_name: 'Episódio 3',
                    code: '02',
                    remarks: '-50',
                },
                {
                    numero_secao: '04',
                    numero_sub_secao: '',
                    numero_block: '02',
                    block_name: 'Episódio 3',
                    code: '03',
                    remarks: '-55',
                },
                {
                    numero_secao: '05',
                    numero_sub_secao: '04',
                    numero_block: '08',
                    block_name: 'Episódio 1',
                    code: '12',
                    remarks: 'ALL',
                },
                {
                    numero_secao: '05',
                    numero_sub_secao: '06',
                    numero_block: '03',
                    block_name: 'Episódio 4',
                    code: '01',
                    remarks: '-60',
                },
                {
                    numero_secao: '05',
                    numero_sub_secao: '06',
                    numero_block: '03',
                    block_name: 'Episódio 4',
                    code: '02',
                    remarks: '-50',
                },
                {
                    numero_secao: 'AP01',
                    numero_sub_secao: '',
                    numero_block: '02',
                    block_name: 'Appendix',
                    code: '01',
                    remarks: 'ALL',
                },
                {
                    numero_secao: 'S03',
                    numero_sub_secao: '',
                    numero_block: '05',
                    block_name: 'Mars',
                    code: '01',
                    remarks: 'ALL',
                },
                {
                    numero_secao: 'S03',
                    numero_sub_secao: '',
                    numero_block: '10',
                    block_name: 'Copyright',
                    code: '01',
                    remarks: '-50,-55',
                },
                {
                    numero_secao: 'S03',
                    numero_sub_secao: '',
                    numero_block: '10',
                    block_name: 'Copyright',
                    code: '02',
                    remarks: '-60',
                },
            ]
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

        sendFile() {

        }
    },
}