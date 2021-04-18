package com.fatec.mom.infra.codelist.reader.domain;

import com.fatec.mom.infra.codelist.reader.config.SingleCellData;
import com.fatec.mom.infra.codelist.reader.metadataretrievers.MetadataRetriever;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter(AccessLevel.PROTECTED)
public abstract class CellBasedMetadataRetriever implements MetadataRetriever {

    private final SingleCellData data;
}
