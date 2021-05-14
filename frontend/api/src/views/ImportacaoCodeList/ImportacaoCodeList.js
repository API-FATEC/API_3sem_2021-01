import { http } from "../../services/config";

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
            formData.append("files", this.files);

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

                // var extPermitidas = ['jpg', 'png', 'gif', 'pdf', 'txt', 'doc', 'docx'];
                // var extArquivo = f.name.split('.').pop().toLowerCase();

                // if (typeof extPermitidas.find(function (ext) { return extArquivo == ext; }) == 'undefined') {
                //     alert('Extensão "' + extArquivo + '" não permitida!');
                //     console.log(f.name);
                //     return
                // } else {
                //     alert('Ok!');
                //     this.files.push(f);
                // }

                this.files.push(f);
            });
        },

        removeFile(fileKey) {
            this.files.splice(fileKey, 1)
        },

        converteMB(x) {
            var sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
            if (x == 0) return '0 Byte';
            var i = parseInt(Math.floor(Math.log(x) / Math.log(1024)));
            return Math.round(x / Math.pow(1024, i), 2) + ' ' + sizes[i];
        },
    }
}
