package com.fatec.mom.domain.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileInfoService {

    @Autowired
    private Reader xlsReader;

    public FileInfo build(final String fileName) {
        return FileInfo.builder()
                .fileName(fileName)
                .actualIndex(1)
                .totalRows(xlsReader.getSize(fileName))
                .build();
    }
}
