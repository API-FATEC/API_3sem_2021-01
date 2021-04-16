import {BlockFactory, BlockRequestBody} from "./Block";

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

    addBlock(block) {
        if (this.blocks != null) {
            this.blocks.push(block);
        }
    }

    get id() {
        return this._id;
    }

    get createdDate() {
        return this._createdDate;
    }

    get name() {
        return this._name;
    }

    get partNumber() {
        return this._partNumber;
    }

    get blocks() {
        return this._blocks;
    }
}

export class DocumentFactory {

    static createFromResponse(data) {
        let blocks = [];
        data.blocks.forEach(function (block) {
            blocks.push(BlockFactory.createFromResponse(block));
        });
        return new Document(data.id, data.createdDate, data.name, data.partNumber, data.trait, blocks);
    }

    static createWithEmptyBlocks(doc) {
        return new Document(doc.id, doc.createdDate, doc.name, doc.partNumber, doc.trait, []);
    }

    static createBodyRequest(doc, blocks) {
        return {
            id: doc.id,
            createdDate: doc.createdDate,
            name: doc.name,
            partNumber: doc.partNumber,
            trait: doc.trait,
            blocks: blocks
        };
    }
}

export class DocumentRequestBody {

    static createList(docs) {
        let docList = [];
        for (let i = 0; i < docs.length; ++i) {
            let blocks = BlockRequestBody.createList(docs[i].blocks);
            let doc = DocumentFactory.createBodyRequest(docs[i], blocks);
            docList.push(doc);
        }
        return docList;
    }
}