package com.fatec.mom.domain.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FileInfoService {

    @Autowired
    private Reader xlsReader;

    public FileInfo build(final String fileName) throws IOException {
        return FileInfo.builder()
                .fileName(fileName)
                .actualIndex(1)
                .totalRows(xlsReader.getSize(fileName))
                .build();
    }
}
