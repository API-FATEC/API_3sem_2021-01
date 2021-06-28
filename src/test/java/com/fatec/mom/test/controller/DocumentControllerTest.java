package com.fatec.mom.test.controller;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.block.BlockLink;
import com.fatec.mom.domain.block.BlockService;
import com.fatec.mom.domain.block.BlockStatus;
import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentService;
import com.fatec.mom.domain.revision.RevisionTag;
import com.fatec.mom.domain.tag.Tag;
import com.fatec.mom.domain.tag.TypeTag;
import com.fatec.mom.domain.trait.Trait;
import com.fatec.mom.test.integration.IntegrationTest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
//import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@IntegrationTest
class DocumentControllerTest extends AbstractControllerTest {

    @Autowired
    private DocumentService documentService;

//    @Test
//    @Sql(value = "/com/fatec/mom/test/sql/insert-three-documents-and-twenty-five-blocks.sql",
//        config = @SqlConfig(transactionManager = "dataSourceTransactionManager"))
//    void givenARequestToFindByNameAndPartNumberShouldReturn200AndTheListOfDocuments() throws Exception {
//        var result = getMockMvc().perform(get("/document/find/all/by?document_name=ABC&part_number=1234"))
//                .andExpect(status().isOk())
//                .andReturn();
//        var json = getJsonArray(result);
//
//        JSONAssert.assertEquals(
//                jsonAsString("expected-doc-search-by-request-as-list.json"),
//                getResultAsJson(result),
//                true);
//
//        assertThat(json.length(), equalTo(3));
//    }
//
//    @Test
//    @Sql(value = "/com/fatec/mom/test/sql/insert-four-documents.sql",
//            config = @SqlConfig(transactionManager = "dataSourceTransactionManager"))
//    void givenARequestToFindAllDocsShouldReturn200AndAllTheDocuments() throws Exception {
//        var result = getMockMvc().perform(get("/document/find/all"))
//                .andExpect(status().isOk())
//                .andReturn();
//        var json = getJsonArray(result);
//
//        JSONAssert.assertEquals(
//                jsonAsString("expected-four-documents-as-list.json"),
//                getResultAsJson(result),
//                true);
//
//        assertThat(json.length(), equalTo(4));
//    }
//
//    @Test
//    @Sql(value = "/com/fatec/mom/test/sql/insert-four-documents.sql",
//            config = @SqlConfig(transactionManager = "dataSourceTransactionManager"))
//    void givenARequestToFindASpecificDocShouldReturn200AndOnlyOneDocument() throws Exception {
//        var result = getMockMvc().perform(get("/document/find?document_name=Modelo_1&part_number=1234&trait=60"))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        JSONAssert.assertEquals(
//                jsonAsString("expected-one-document.json"),
//                getResultAsJson(result),
//                true);
//    }
//
//    @Test
//    @Sql(value = "/com/fatec/mom/test/sql/insert-three-documents-and-twenty-five-blocks.sql",
//            config = @SqlConfig(transactionManager = "dataSourceTransactionManager"))
//    void shouldUpdateAllDocs() throws Exception {
//        var docs = documentService.findAllByNameAndPartNumber("ABC", 1234);
//        assertThat(docs.size(), equalTo(3));
//        assertThat(docs.get(0).getBlocks().size(), equalTo(12));
//
//        changeDocsContent(docs);
//        assertThat(docs.size(), equalTo(3));
//        assertThat(docs.get(0).getBlocks().size(), equalTo(1));
//
//        var updatedDocs = getMockMvc().perform(
//                    put("/document/update/all", docs)
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(asJsonString(docs))
//                )
//                .andExpect(status().isOk())
//                .andReturn();
//
//        var listType = new TypeToken<LinkedList<Document>>(){}.getType();
//        docs = new Gson().fromJson(updatedDocs.getResponse().getContentAsString(), listType);
//
//        assertThat(docs.size(), equalTo(3));
//        assertThat(docs.get(0).getBlocks().size(), equalTo(1));
//    }
//
//    private void changeDocsContent(List<Document> documentsToEdit) {
//        var blocks = new HashSet<Block>();
//        blocks.add(Block.builder().section("0").number(10).name("testeBloco1").code(50).order(1).build());
//        documentsToEdit.get(0).setBlocks(blocks);
//    }
//
//    @Test
//    @Sql(value = "/com/fatec/mom/test/sql/insert-three-documents-and-twenty-five-blocks.sql",
//            config = @SqlConfig(transactionManager = "dataSourceTransactionManager"))
//    void givenADocumentNameShouldReturnAllPartNumbers() throws Exception {
//        var result = getMockMvc().perform(get("/document/find/part_number/by/name?document_name=ABC"))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        JSONAssert.assertEquals(
//                jsonAsString("expected-part-numbers.json"),
//                getResultAsJson(result),
//                true);
//    }

    @Test
    void generateFull() throws Exception {
        //testa o url pra ver se vai funcionar
        getMockMvc().perform(
                get("/document/download/full?trait=50")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        //se funcionou agora o arquivo gerado será apagado
        File currentDirFile = new File(".");
        String helper = currentDirFile.getAbsolutePath();
        String currentDir = helper.substring(0, helper.length() - 1);
        String rootPath = currentDir + "doc\\Mockup FATEC\\MOCKUP\\ABC-1234\\Master\\";
        String[] split = currentDir.split("\\\\");
        File desktop = new File(System.getProperty("user.home"), "Desktop");
        File full50 = new File(desktop.getPath() + "\\[]ABC-1234-50-FULL.pdf");
        full50.delete();
    }

    //criado pra referencia durante a construção de getData
    @Test
    @Sql(value = "/com/fatec/mom/test/sql/all-tables.sql",
            config = @SqlConfig(transactionManager = "dataSourceTransactionManager"))
    void lepData() throws Exception {
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
            add(Trait.builder().number(55).build());
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
        Block block2 = Block.builder()
                .section("02")
                .code(50)
                .name("Lep")
                .number(1)
                .order(1)
                .status(BlockStatus.IN_REVISION)
                .basePath("//Documentos")
                .links(links)
                .tags(tags)
                .traits(traits)
                .build();
        ListBlocks.add(block2);
        Set<Block> SetBlocks = new HashSet<>();
        SetBlocks.add(block);
        SetBlocks.add(block2);
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

        System.out.println(document.toString());
        System.out.println(document.getBlocks());



        var result = getMockMvc().perform(
                post("/document/save", document)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(document))
        ).andExpect(status().isOk()).andReturn();


        var doc = getMockMvc().perform(
                get("/document/find?document_name=DocumentoTeste1&part_number=1&trait=50")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        System.out.println(getJsonArray(doc).get(0));
        JsonObject json = JsonParser.parseString(getJsonArray(doc).get(0).toString()).getAsJsonObject();
        var a = json.get("traits");

        System.out.println(a);
        System.out.println(a.getAsJsonArray().get(0).getAsJsonObject().get("number"));

        for (int i = 0; i < a.getAsJsonArray().size(); i++) {
            System.out.println(a.getAsJsonArray().get(i).getAsJsonObject().get("id"));
        }
    }
}
