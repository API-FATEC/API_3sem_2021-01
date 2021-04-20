import {CodelistTableDescriptor} from "./CodelistTableDescriptor";

export class CodelistTableGenerator {

    constructor(codelistFactory) {
        this.codelistFactory = codelistFactory;
    }

    generateCodelistTable(data, defaultItemTemplate) {
        const generateColumns = function(codelist ) {
            const columnsToAdd = [];
            let documentsLength = codelist.documents.length;

            for (let i = 0; i < documentsLength; ++i) {
                let value = codelist.documents[i].trait;
                columnsToAdd.push(value);
            }

            return columnsToAdd;
        }

        const generateHeaders = function(columns) {
            const headers = [];
            columns.forEach(function (column) {
                headers.push({text: `Traço: ${column}`, value: `trait_${column}`});
            });
            return headers;
        }

        const generateTraits = function(columns) {
            const traits = [];
            columns.forEach(function (column) {
                traits.push(`trait_${column}`)
            });
            return traits;
        }

        const generateBlocks = function(codelist, traits) {
            let codelistBlocks = [];
            codelist.codelistBlocks.forEach(function (codelistBlock) {
                let objeto = {...codelistBlock.block};
                for (let i = 0; i < traits.length; ++i) {
                    objeto[traits[i]] = codelistBlock.checklist[i].valueOf();
                }
                codelistBlocks.push(objeto);
            });

            return codelistBlocks;
        }

        const sortCodelistBlocks = function(array) {
            function compare(a, b) {
                return (a.order < b.order) ? -1 : (a.order > b.order) ? 1 : 0;
            }
            return array.sort(compare);
        }

        const generateDefaultItem = function(defaultItemTemplate, traits) {
            let blockEditedItem = defaultItemTemplate;
            for (let i = 0; i < traits.length; ++i) {
                let obj = {};
                obj[traits[i]] = 0;
                blockEditedItem = {...blockEditedItem, ...obj};
            }

            return blockEditedItem;
        }

        const codelist = this.codelistFactory.createFromResponse(data);
        const columns = generateColumns(codelist);

        const headers = generateHeaders(columns);
        const traits = generateTraits(columns);
        const desserts = sortCodelistBlocks(generateBlocks(codelist, traits));
        const defaultItem = generateDefaultItem(defaultItemTemplate, traits);

        return new CodelistTableDescriptor({
            headers: headers,
            defaultItem: defaultItem,
            traits: traits,
            desserts: desserts
        });
    }
}