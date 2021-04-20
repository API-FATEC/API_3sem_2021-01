const defaultPath = '/codelist';

export class CodelistService {

    constructor(http) {
        this.http = http;
    }

    getCodelist(documentName, partNumber) {
        return this.http.getWithParams(`${defaultPath}/find`, {
            document_name: documentName,
            part_number: partNumber
        });
    }
}