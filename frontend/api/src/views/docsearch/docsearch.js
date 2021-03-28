import { http } from "../../services/config";
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
        trait: '',
        response: []
        
    }),

    methods: {
        validate() {
            this.$refs.form.validate()
        },
        reset() {
            this.$refs.form.reset()
        },
        searchDoc() {
            this.validate();

            const formData = new FormData();
            formData.append("document_name", this.name);
            formData.append("part_number", this.partNumber);
            formData.append("trait", this.trait);

            http.get('/document/find',formData).then(response => this.response = response.data);
        }
    },

}