export class Block {
    constructor(id, section, subSection, number, name, code, order) {
        this.id = id;
        this.section = section;
        this.subSection = subSection;
        this.number = number;
        this.name = name;
        this.code = code;
        this.order = order;

    }

    static compare(a, b) {
        if (a.order < b.order)
            return -1;
        if (a.order > b.order)
            return 1;
        return 0;
    }
}