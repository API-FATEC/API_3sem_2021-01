const defaultPath = '/codelist';

export class CodelistService {

    constructor(http) {
        this.http = http;
    }

    getCodelist(documentName, partNumber) {
        return this.http.getWithParams(`${defaultPath}/find`, {
            document_name: documentName,
            part_number: partNumber
        });
    }

    saveCodelist(defaultCodelist, document, codelistBlockFactory) {
        const generateCodelist = function(defaultCodelist, document, codelistBlockFactory) {
            const codelistBlocks = [];
            defaultCodelist.forEach(function (codelistBlock) {
                const block =  codelistBlockFactory.createFromCodelistDessert(codelistBlock, document.traits);
                codelistBlocks.push(block);
            });
            return codelistBlocks;
        }

        const codelist = generateCodelist(defaultCodelist, document, codelistBlockFactory);

        return this.http.putWithParams(`${defaultPath}/save`, codelist, {
            document_name: document.name,
            part_number: document.partNumber,
            traits: document.traits
        });
    }
}