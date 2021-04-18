package com.fatec.mom.infra.codelist.reader.config;

import com.fatec.mom.infra.codelist.reader.metadataretrievers.MetadataRetriever;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Getter
public class CodelistSheetConfig {

    private final HeaderCellData columns;
    private final SingleCellData row;


    private final List<MetadataRetriever> metadataRetrievers = new LinkedList<>();

    public CodelistSheetConfig(final HeaderCellData columns,
                               final SingleCellData row,
                               final List<MetadataRetriever> metadataRetrievers) {
        this.columns = columns;
        this.row = row;
        this.metadataRetrievers.addAll(metadataRetrievers);
    }
}
