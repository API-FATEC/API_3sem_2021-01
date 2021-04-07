package com.fatec.mom.domain.file;

import lombok.*;

@Builder @AllArgsConstructor
public class FileInfo {

    @Getter
    private String fileName;

    @Getter @Setter
    private int actualIndex;

    @Getter @Setter
    private int totalRows;
}
