export class Block {
    constructor(id, section, subSection, number, name, code, order, documents) {
        this._id = id;
        this._section = section;
        this._subSection = subSection;
        this._number = number;
        this._name = name;
        this._code = code;
        this._order = order;
        this._documents = documents;
    }

    equals(block) {
        return block.section() === this.section()
            && block.subSection() === this.subSection()
            && block.number() === this.number()
            && block.name() === this.name()
            && block.code() === this.code()
            && block.order() === this.order();
    }
    
    static createFromResponse(data) {
        return new Block(data._id, data._section, data._subSection, data._name, data._code, data._order);
    }

    set documents(value) {
        this.documents = value;
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

    get documents() {
        return this._documents;
    }
}