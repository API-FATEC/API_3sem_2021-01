package com.fatec.mom.test.domain.document;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.document.Document;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Date;
import java.util.HashSet;

public class DocumentBuilderTest {

    @Test
    public void constructDocumentUsingBuilder() {
        var doc = Document.builder()
                .id(1L)
                .createdDate(new Date())
                .name("test")
                .partNumber(123)
                .trait(50)
                .blocks(new HashSet<Block>())
                .build();

        System.out.println(doc.toString());
        assertThat(doc, instanceOf(Document.class));
    }
}
