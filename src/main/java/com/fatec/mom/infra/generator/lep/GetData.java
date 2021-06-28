package com.fatec.mom.infra.generator.lep;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.document.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetData {

    @Autowired
    private DocumentService documentService;

    public List<List<String>> getData (String documentName, int partNumber, int trait) {
        List<List<String>> data = new ArrayList<>();
        //executa diretamente o Service que busca no banco
        var documents = documentService.findAllByNameAndPartNumberAndTrait(documentName, partNumber, trait);
        //cria um iterator para poder pegar o document, porque o Service retorna um Set
        var doc = documents.iterator().next();

        //cria um iterator pros blocos dentro do document, loopa pra cada bloco
        for (Iterator<Block> blocks = doc.getBlocks().iterator(); blocks.hasNext();) {
            var singleBlock = blocks.next();
            //cria uma lista onde é adicionado o nome e codigo do bloco
            List<String> content = new ArrayList<>();
            content.add(singleBlock.getSection());
            content.add(singleBlock.getSubSection());
            content.add(String.valueOf(singleBlock.getNumber()));
            content.add(singleBlock.getName());
            content.add(String.valueOf(singleBlock.getCode()));

            data.add(content);
        }

        return data;
    }
}
