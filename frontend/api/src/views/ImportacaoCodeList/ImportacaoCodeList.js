import { http } from "../../services/config";
import swal from 'sweetalert';

export default {
    data: () => ({
        IMPORT_URI: '/codelist/import',
        valid: true,
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
            for (let file of this.files) {
                formData.append("files", file, file.name);
            }

            http.post(this.IMPORT_URI, formData).then(
                response => {
                    console.log(response);
                    this.response = response.data;
                    this.reset();
                },
                swal("Sucesso!", "Arquivo salvo com sucesso!", "success")
            );
        },

        removeFile(fileKey) {
            this.files.splice(fileKey, 1)
        },
    }
}
