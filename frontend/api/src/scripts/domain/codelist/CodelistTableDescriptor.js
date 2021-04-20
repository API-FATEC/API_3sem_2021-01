export class CodelistTableDescriptor {

    constructor({headers, defaultItem, documentTraits, desserts}) {
        this._headers = headers;
        this._defaultItem = defaultItem;
        this.traits = documentTraits;
        this._desserts = desserts;
    }


    get headers() {
        return this._headers;
    }

    set headers(value) {
        this._headers = value;
    }

    get defaultItem() {
        return this._defaultItem;
    }

    set defaultItem(value) {
        this._defaultItem = value;
    }

    get traits() {
        return this.traits;
    }

    set traits(value) {
        this.traits = value;
    }

    get desserts() {
        return this._desserts;
    }

    set desserts(value) {
        this._desserts = value;
    }
}