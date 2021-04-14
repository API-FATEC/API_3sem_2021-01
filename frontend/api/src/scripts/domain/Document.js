export class Document {
    constructor(id, createdDate, name, partNumber, trait, blocks) {
        this.id = id;
        this.createdDate = createdDate;
        this.name = name;
        this.partNumber = partNumber;
        this.trait = trait;
        this.blocks = blocks;
    }

    getBlocksChecklist(blocksToCheck) {
        if (this.blocks === undefined) { return []; }

        const list = [];
        blocksToCheck.forEach(function (block) {
            if (this.blocks.contains(block)) {
                list.push(1);
            } else {
                list.push(0);
            }
        });

        return list;
    }

    static parseDocuments(jsonResponse) {
        if (jsonResponse === undefined) {
            return null;
        }

        return JSON.parse(jsonResponse);
    }
}