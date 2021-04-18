package com.fatec.mom.infra.codelist.reader.config;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class CellData {

    @Getter
    private final int column;
}
