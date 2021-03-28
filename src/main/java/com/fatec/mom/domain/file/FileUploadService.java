package com.fatec.mom.domain.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Serviço responsável por salvar os arquivos importados.
 *
 * @author Tobias Lino
 * @version v01 26/03/2021
 */
@Service
public class FileUploadService {

    @Value("${default-upload-path}")
    private String defaultPath;

    public void uploadFile(MultipartFile file) throws IllegalStateException ,IOException {
        file.transferTo(new File(String.format("%s/%s", defaultPath, file.getOriginalFilename())));
    }
}
