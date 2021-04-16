export class Document {
    constructor(id, createdDate, name, partNumber, trait, blocks) {
        this._id = id;
        this._createdDate = createdDate;
        this._name = name;
        this._partNumber = partNumber;
        this._trait = trait;
        this._blocks = blocks;
    }

    get trait() {
        return this._trait;
    }
}

export class DocumentFactory {

    static createFromResponse(data) {
        return new Document(data.id, data.createdDate, data.name, data.partNumber, data.trait, data.blocks);
    }
}