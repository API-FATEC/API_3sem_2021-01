package com.fatec.mom.domain.codelist;

import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.file.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodelistConverterService {

    @Autowired
    private CodelistConverterChain converterChain;

    public void convertFileDataIntoDocuments(Document referenceDocument, final FileInfo fileInfo) {
        converterChain.doFilters(referenceDocument, fileInfo, null);
    }
}
