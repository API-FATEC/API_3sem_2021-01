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
        response: [],
        documentBlocks: [],
        
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

            const params = new URLSearchParams();
            params.append("document_name", this.name);
            params.append("part_number", this.partNumber);
            params.append("trait", this.trait);

            http.get('/document/find', {
                params: {
                    document_name: this.name,
                    part_number: this.partNumber,
                    trait: this.trait
                }
            }).then(response => {
                console.log(response.data)
                this.response = this.sortedArray(response.data[0].blocks);
            });
        }, sortedArray: function(array) {
            function compare(a, b) {
                if (a.order < b.order)
                    return -1;
                if (a.order > b.order)
                    return 1;
                return 0;
            }

            return array.sort(compare);
        }
    },
}