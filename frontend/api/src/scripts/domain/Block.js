export class Block {
    constructor(id, section, subSection, number, name, code, order) {
        this._id = id;
        this._section = section;
        this._subSection = subSection;
        this._number = number;
        this._name = name;
        this._code = code;
        this._order = order;
    }

    equals(block) {
        return block.section === this.section
            && block.subSection === this.subSection
            && block.number === this.number
            && block.name === this.name
            && block.code === this.code;
    }

    get id() {
        return this._id;
    }

    get section() {
        return this._section;
    }

    get subSection() {
        return this._subSection;
    }

    get number() {
        return this._number;
    }

    get name() {
        return this._name;
    }

    get code() {
        return this._code;
    }

    get order() {
        return this._order;
    }
}

export class BlockFactory {

    static createFromResponse(data) {
        return new Block(data.id, data.section, data.subSection, data.name, data.code, data.order);
    }

    static createFromCodelist(codelist) {
        return new Block(codelist.id, codelist.section, codelist.subSection, codelist.number, codelist.name, codelist.code, codelist.order);
    }

    static createBodyRequest(block) {
        return {
            id: block.id,
            section: block.section,
            subSection: block.subSection,
            number: block.number,
            name: block.name,
            code: block.code,
            order: block.order
        };
    }
}

export class BlockRequestBody {

    static createList(blocks) {
        let blockList = [];
        for (let i = 0; i < blocks.length; ++i) {
            let block = BlockFactory.createBodyRequest(blocks[i]);
            blockList.push(block);
        }
        return blockList;
    }
}