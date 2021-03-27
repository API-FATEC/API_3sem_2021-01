package com.fatec.mom.domain.file;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileInfo {

    private String fileName;
    private String path;

    private int actualIndex;
    private int totalRows;
}
