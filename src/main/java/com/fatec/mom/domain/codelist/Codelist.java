package com.fatec.mom.domain.codelist;

import com.fatec.mom.domain.document.Document;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class Codelist {

    private List<Document> documents;
    private Set<CodelistBlock> codelistBlocks;
}
