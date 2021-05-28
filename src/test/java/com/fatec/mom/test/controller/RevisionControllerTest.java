package com.fatec.mom.test.controller;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.block.BlockLink;
import com.fatec.mom.domain.block.BlockStatus;
import com.fatec.mom.domain.revision.Revision;
import com.fatec.mom.domain.revision.RevisionService;
import com.fatec.mom.domain.revision.RevisionStatus;
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

        Set<BlockLink> links = new HashSet<>() {{
            add(BlockLink.builder().fileName("TestFileLetter").extension("pdf").upload(dateFormat.parse("01/03/2021")).build());
        }};
        Set<Tag> tags = new HashSet<>(){{
            add(new Tag(null, "TagTest", dateFormat.parse("01/03/2021"), new TypeTag("TypeTagTest")));
        }};
        Set<Trait> traits = new HashSet<>() {{
            add(Trait.builder().number(50).build());
        }};
        List<Block> blocks = new ArrayList<>();
        blocks.add(
                Block.builder()
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
                        .build());
        Revision rev = Revision.builder()
                .name("Revision1")
                .status(RevisionStatus.OPENED)
                .createdDate(dateFormat.parse("01/03/2021"))
                .lastUpdateDate(dateFormat.parse("08/04/2021"))
                .blocksInRevision(blocks)
                .build();

        var result = getMockMvc().perform(
                post("/revision/save", rev)
                .contentType(MediaType.APPLICATION_JSON)
                .content((asJsonString(rev)))
        ).andExpect(status().isOk()).andReturn();

        System.out.println(getResultAsJson(result));
        JSONAssert.assertEquals(
                jsonAsString("expected-one-revision-with-one-block.json"),
                getResultAsJson(result),
                true);
    }
    /*
    @Test
    @Sql(value = "/com/fatec/mom/test/sql/all-tables.sql",
    config = @SqlConfig(transactionManager = "dataSourceTransactionManager"))
    */
}
