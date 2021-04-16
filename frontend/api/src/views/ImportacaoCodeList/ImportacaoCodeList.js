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
        files: [],
        response: [],
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
                    this.response = response.data;
                    this.reset();
                }
            );
        },

        handleFileDrop(e) {
            let droppedFiles = e.dataTransfer.files;
            if (!droppedFiles) return;
            ([...droppedFiles]).forEach(f => {
                this.files.push(f);
            });
        },

        handleFileInput(e) {
            let files = e.target.files
            if (!files) return;
            ([...files]).forEach(f => {
                this.files.push(f);
            });
        },

        removeFile(fileKey) {
            this.files.splice(fileKey, 1)
        },
    },
}