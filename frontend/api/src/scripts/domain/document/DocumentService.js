const defaultPath = '/document'

export class DocumentService {

    constructor(http) {
        this._http = http;
    }

    get http() {
        return this._http;
    }

    getDocNames() {
        return this.http.get(`${defaultPath}/find/name/all`);
    }

    getPartNumbers(name) {
        return this.http.getWithParams(`${defaultPath}/find/part_number/by/name`, {
            params: {
                document_name: name
            }
        });
    }
}