package com.fatec.mom.test.domain.document;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentRepository;
import com.fatec.mom.test.integration.AbstractIntegrationTest;
import com.fatec.mom.test.integration.IntegrationTest;
import com.google.gson.Gson;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@IntegrationTest
class DocumentRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private DocumentRepository documentRepository;

    @Test
    @Sql(value = "/com/fatec/mom/test/sql/docs-blocks-tables.sql")
    @Transactional
    void givenADocumentWithASetOfBlocksShouldPersistAllObjects() throws JSONException {
        var document = sampleDocument();

        var savedDocument = documentRepository.save(document);
        var jsonResult = new Gson().toJson(savedDocument);

        JSONAssert.assertEquals(jsonAsString("expected-sample-document-and-blocks.json"), jsonResult, true);
    }

    private Document sampleDocument() {
        return Document.builder()
                .name("ABC")
                .partNumber(1234)
                .trait(50)
                .blocks(sampleBlocks())
                .build();
    }

    private Set<Block> sampleBlocks() {
        var blocks = new HashSet<Block>();

        blocks.add(Block.builder().section("0").number(0).name("Letter").code(50).order(1).build());
        blocks.add(Block.builder().section("0").number(1).name("Cover").code(1).order(2).build());
        blocks.add(Block.builder().section("0").number(2).name("LEP").code(1).order(3).build());
        blocks.add(Block.builder().section("0").number(3).name("TOC").code(1).order(4).build());
        blocks.add(Block.builder().section("2").number(4).name("Introduction").code(1).order(5).build());
        blocks.add(Block.builder().section("3").subSection("1").number(3).name("Episodio 2").code(14).order(6).build());
        blocks.add(Block.builder().section("4").number(2).name("Episodio 3").code(2).order(7).build());
        blocks.add(Block.builder().section("5").subSection("4").number(8).name("Episodio 1").code(12).order(8).build());
        blocks.add(Block.builder().section("5").subSection("6").number(3).name("Episodio 4").code(2).order(9).build());
        blocks.add(Block.builder().section("AP01").number(2).name("Appendix").code(1).order(10).build());
        blocks.add(Block.builder().section("S03").number(0).name("Mars").code(1).order(11).build());
        blocks.add(Block.builder().section("S03").number(0).name("Copyright").code(2).order(12).build());

        return blocks;
    }
}
