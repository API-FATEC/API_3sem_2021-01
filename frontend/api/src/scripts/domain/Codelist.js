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

    static createFromResponse(responseData) {
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

    static createFromResponse(data) {
        return new CodelistBlock(data.block, data.checklist);
    }
}