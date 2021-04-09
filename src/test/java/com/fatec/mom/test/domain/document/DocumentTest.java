package com.fatec.mom.test.domain.document;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.document.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class DocumentTest {

    @Test
    void testAllGeneratedMethods() throws ParseException {
        var blocks = new HashSet<Block>();
        var doc1 = new Document();
        var doc2 = simpleDocument(blocks);

        doc1.setId(1L);
        doc1.setCreatedDate(simpleDate("24/10/2021"));
        doc1.setName("test");
        doc1.setPartNumber(123);
        doc1.setTrait(50);
        doc1.setBlocks(blocks);

        assertThat(doc1.toString(), equalTo(doc2.toString()));
        assertThat(doc1.equals(doc2), equalTo(true));
        assertThat(doc1.equals(new Document()), equalTo(false));
        assertThat(doc1.hashCode(), notNullValue());
        assertThat(doc2.hashCode(), notNullValue());
    }

    Document simpleDocument(final Set<Block> blocks) throws ParseException {
        return Document.builder()
                .id(1L)
                .createdDate(simpleDate("24/10/2021"))
                .name("test")
                .partNumber(123)
                .trait(50)
                .blocks(blocks)
                .build();
    }

    Date simpleDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.parse(date);
    }
}
