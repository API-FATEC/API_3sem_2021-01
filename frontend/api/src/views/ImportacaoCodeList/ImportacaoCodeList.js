import { http } from "../../services/config";

export default {
    data: () => ({
        IMPORT_URI: '/codelist/import',
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
        file: [],

        result: [],
    }),

    methods: {
        validate() {
            this.$refs.form.validate()
        },
        reset() {
            this.$refs.form.reset()
        },

        sendFile() {
            this.validate();

            const formData = new FormData();
            formData.append("document_name", this.name);
            formData.append("part_number", this.partNumber);
            formData.append("file", this.file);

            http.post(this.IMPORT_URI, formData).then(
                response => {
                    console.log(response);
                    alert("Salvo com sucesso!!")
                    this.reset();
                }
            );
        },
    },
}