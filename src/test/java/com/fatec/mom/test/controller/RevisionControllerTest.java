package com.fatec.mom.test.controller;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.block.BlockLink;
import com.fatec.mom.domain.block.BlockStatus;
import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.revision.Revision;
import com.fatec.mom.domain.revision.RevisionService;
import com.fatec.mom.domain.revision.RevisionStatus;
import com.fatec.mom.domain.revision.RevisionTag;
import com.fatec.mom.domain.tag.Tag;
import com.fatec.mom.domain.tag.TypeTag;
import com.fatec.mom.domain.trait.Trait;
import com.fatec.mom.test.integration.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
class RevisionControllerTest extends AbstractControllerTest{

    @Autowired
    private RevisionService revisionService;

    @Test
    @Sql(value = "/com/fatec/mom/test/sql/all-tables.sql",
    config = @SqlConfig(transactionManager = "dataSourceTransactionManager"))
    void shouldAddOneRev() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        Date date1 = dateFormat.parse("01/03/2021");
        Date date2 = dateFormat.parse("08/04/2021");
        Set<BlockLink> links = new HashSet<>() {{
            add(BlockLink.builder().fileName("TestFileLetter").extension("pdf").upload(date1).build());
        }};
        Set<Tag> tags = new HashSet<>(){{
            add(new Tag(null, "TagTest", date1, new TypeTag("TypeTagTest")));
        }};
        Set<Trait> traits = new HashSet<>() {{
            add(Trait.builder().number(50).build());
        }};
        Block block = Block.builder()
                .section("00")
                .code(50)
                .name("Letter")
                .number(0)
                .order(1)
                .status(BlockStatus.IN_REVISION)
                .basePath("//Documentos")
                .links(links)
                .tags(tags)
                .traits(traits)
                .build();
        List<Block> ListBlocks = new ArrayList<>();
        ListBlocks.add(block);
        Set<Block> SetBlocks = new HashSet<>();
        SetBlocks.add(block);
        Set<RevisionTag> RTags = new HashSet<>();
        RTags.add(RevisionTag.builder()
                .value("RTAGTeste")
                .revision(null)
                .build());
        Document document = Document.builder()
                .id((long) 1)
                .createdDate(date1)
                .name("DocumentoTeste1")
                .partNumber(1)
                .revisions(null)
                .tags(tags)
                .traits(traits)
                .blocks(SetBlocks)
                .build();
        Revision rev = Revision.builder()
                .name("Revision1")
                .status(RevisionStatus.OPENED)
                .createdDate(date1)
                .lastUpdateDate(date2)
                .document(document)
                .blocksInRevision(ListBlocks)
                .revisionTags(RTags)
                .build();
        System.out.println(rev);
        System.out.println(rev.getDocument());
        var result = getMockMvc().perform(
                post("/revision/save", rev, document)
                .contentType(MediaType.APPLICATION_JSON)
                .content((asJsonString(rev)))
        ).andExpect(status().isOk()).andReturn();

        System.out.println(document);
        System.out.println(getResultAsJson(result));
        JSONAssert.assertEquals(
                jsonAsString("expected-one-revision-with-one-block.json"),
                getResultAsJson(result),
                true);
    }

}
