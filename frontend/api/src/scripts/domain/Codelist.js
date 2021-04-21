import {DocumentFactory} from "./Document";

export class Codelist {
    constructor(documents, codelistBlocks) {
        this._documents = documents;
        this._codelistBlocks = codelistBlocks;
    }

    get documents() {
        return this._documents;
    }

    get codelistBlocks() {
        return this._codelistBlocks;
    }
}

export class CodelistBlock {
    constructor(block, checklist) {
        this._block = block;
        this._checklist = checklist;
    }

    get block() {
        return this._block;
    }

    get checklist() {
        return this._checklist;
    }
}

export class CodelistFactory {

    createFromResponse(responseData) {
        const documents = [];
        responseData.documents.forEach(function (doc) {
            documents.push(DocumentFactory.createFromResponse(doc))
        });

        const codelistBlocks = [];
        responseData.codelistBlocks.forEach(function (codelistBlock) {
            codelistBlocks.push(CodelistBlockFactory.createFromResponse(codelistBlock))
        });

        return new Codelist(documents, codelistBlocks);
    }
}

export class CodelistBlockFactory {

    constructor(blockFactory) {
        this.blockFactory = blockFactory;
    }

    createFromCodelistDessert(dessert, traits) {
        return new CodelistBlock(this.blockFactory.createFromCodelistBlock(dessert),
            this.createBlocksChecklistFromCodelistDessert(dessert, traits));
    }

    createBlocksChecklistFromCodelistDessert(dessert, traits) {
        const checklist = [];
        traits.forEach(function (trait) {
            let value = trait.substring(trait.indexOf('_'), trait.length);
            checklist.push(value);
        });
        return checklist;
    }
}