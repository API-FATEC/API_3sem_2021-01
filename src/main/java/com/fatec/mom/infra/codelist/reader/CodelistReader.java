package com.fatec.mom.infra.codelist.reader;

import com.fatec.mom.domain.document.Document;
import com.fatec.mom.infra.codelist.reader.config.CodelistConfigType;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

public interface CodelistReader {

    List<Document> readCodelist(final InputStream stream);
}
