export class HttpRequester {

    constructor(http) {
        this._http = http;
    }

    get http() {
        return this._http;
    }

    get(path) {
        return this.http.get(path);
    }

    getWithParams(path, params) {
        return this.http.get(path, params);
    }
}