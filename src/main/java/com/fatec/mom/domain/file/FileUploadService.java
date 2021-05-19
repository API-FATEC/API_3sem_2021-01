package com.fatec.mom.domain.file;

import com.fatec.mom.domain.revision.Revision;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Serviço responsável por salvar os arquivos importados.
 *
 * @author Tobias Lino
 * @version v01 26/03/2021
 */
@Service
public class FileUploadService {

    private static final String DEFAULT_FILE_NAME_FORMAT = "uploaded_file_%s";
    private static final String DEFAULT_REV_PATH = "REV";

    @Value("${default-upload-path}")
    private String defaultPath;

    @Value("${default-documents-path}")
    private String defaultDocumentsPath;

    private final UploadedFileService uploadedFileService;

    @Autowired
    public FileUploadService(UploadedFileService uploadedFileService) {
        this.uploadedFileService = uploadedFileService;
    }

    public File uploadFile(MultipartFile multipartFile,
                           final Revision revision) throws IllegalStateException ,IOException, RuntimeException {
        final UploadedFile savedFile = saveFile(multipartFile);
        return write(
                String.format("%s/%s/%s/%s",
                        defaultDocumentsPath,
                        revision.getDocument().getDocument(),
                        DEFAULT_REV_PATH,
                        revision.getName()),
                multipartFile,
                savedFile);
    }

    public File uploadFile(MultipartFile multipartFile) {
        final UploadedFile savedFile = saveFile(multipartFile);
        return write(defaultPath, multipartFile, savedFile);
    }

    private File write(final String path, final MultipartFile file, UploadedFile uploadedFile) {
        final String filePath = String.format("%s/%s", path, uploadedFile.getFileName());
        mkdir(new File(filePath));
        final String fileName = String.format("%s/%s", filePath, file.getOriginalFilename());
        try {
            byte[] bytes = file.getBytes();
            Path pathFile = Paths.get(fileName);

            Files.write(pathFile, bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new File(fileName);
    }

    public void mkdir(File file) throws RuntimeException {
        file.mkdirs();
    }

    public UploadedFile saveFile(MultipartFile file) {
        UploadedFile uploadedFile = UploadedFile.builder()
                .originalFileName(file.getOriginalFilename())
                .type(file.getContentType())
                .extension(FilenameUtils.getExtension(file.getOriginalFilename()))
                .path(defaultPath)
                .build();

        uploadedFile = uploadedFileService.save(uploadedFile);

        uploadedFile.setFileName(getNewFileName(uploadedFile));
        uploadedFile = uploadedFileService.save(uploadedFile);

        return uploadedFile;
    }

    private String getNewFileName(UploadedFile file) {
        return String.format(DEFAULT_FILE_NAME_FORMAT, file.getId());
    }


}
