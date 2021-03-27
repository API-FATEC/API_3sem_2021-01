export default {
    data: () => ({
        valid: true,
        name: '',
        nameRules: [
            v => !!v || 'O Nome do documento é obrigatório!',
            v => (v && v.length <= 3) || 'O Nome deve possuir no máximo 3 caracteres!',
        ],
        partNumber: '',
        partNumberRuler: [
            v => !!v || 'O Part Number é obrigatório!',
            v => (v && v.length <= 4) || 'O Part Number deve possuir no máximo 4 caracteres!',
            //v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
        ],
    }),

    methods: {
        validate() {
            this.$refs.form.validate()
        },
        reset() {
            this.$refs.form.reset()
        },
    },
}