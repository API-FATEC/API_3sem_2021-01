//package com.fatec.mom.test.controller;
//
//import com.fatec.mom.domain.block.Block;
//import com.fatec.mom.domain.block.BlockLink;
//import com.fatec.mom.domain.block.BlockStatus;
//import com.fatec.mom.domain.revision.Revision;
//import com.fatec.mom.domain.revision.RevisionService;
//import com.fatec.mom.domain.revision.RevisionStatus;
//import com.fatec.mom.domain.tag.Tag;
//import com.fatec.mom.domain.tag.TypeTag;
//import com.fatec.mom.domain.trait.Trait;
//import com.fatec.mom.test.integration.IntegrationTest;
//import org.junit.jupiter.api.Test;
//import org.skyscreamer.jsonassert.JSONAssert;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.jdbc.SqlConfig;
//
//import java.util.*;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@IntegrationTest
//class RevisionControllerTest extends AbstractControllerTest{
//
//    @Autowired
//    private RevisionService revisionService;
//
//    @Test
//    @Sql(value = "/com/fatec/mom/test/sql/all-tables.sql",
//    config = @SqlConfig(transactionManager = "dataSourceTransactionManager"))
//    void shouldAddOneRev() throws Exception {
//        Calendar data1 = Calendar.getInstance();data1.clear(); data1.set(2021, 3, 1);Calendar data2 = Calendar.getInstance();data2.clear();data2.set(2021, 4, 8);
//        Set<BlockLink> links = new HashSet<>();links.add(new BlockLink(null, "TestFileLetter", "pdf", data1.getTime()));
//        Set<Tag> tags = new HashSet<>();tags.add(new Tag(null, "TagTest", data1.getTime(), new TypeTag("TypeTagTest")));
//        Set<Trait> traits = new HashSet<>();traits.add(new Trait(null, 50));
//        List<Block> blocks = new ArrayList<Block>();
//        blocks.add(new Block(null, "00", "", 0, "Letter", 50, 01, BlockStatus.IN_REVISION, "//Documentos", links, tags, traits));
//        Revision rev = new Revision (null, "Revision 1", RevisionStatus.OPENED, data2.getTime(), data2.getTime(), blocks);
//
//        var result = getMockMvc().perform(
//                post("/revision/save", rev)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content((asJsonString(rev)))
//        ).andExpect(status().isOk()).andReturn();
//
//        System.out.println(getResultAsJson(result));
//        JSONAssert.assertEquals(
//                jsonAsString("expected-one-revision-with-one-block.json"),
//                getResultAsJson(result),
//                true);
//    }
//}
