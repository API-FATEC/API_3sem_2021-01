package com.fatec.mom.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileUploadService {
        private final static String DEFAULT_PATH = "/home/wallace/Documentos/teste/%s";
        public void uploadFile(MultipartFile file) throws IllegalStateException ,IOException {
            file.transferTo(new File(String.format(DEFAULT_PATH,file.getOriginalFilename())));
        }
}
