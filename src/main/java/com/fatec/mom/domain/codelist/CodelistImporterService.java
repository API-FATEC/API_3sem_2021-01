package com.fatec.mom.domain.codelist;

import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentService;
import com.fatec.mom.infra.codelist.reader.CodelistReader;
import com.fatec.mom.infra.codelist.reader.CodelistReaderLocator;
import com.fatec.mom.infra.codelist.reader.CodelistReaderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CodelistImporterService {

    private final CodelistReaderLocator readerLocator;
    private final DocumentService documentService;

    @Value("${default-upload-path}")
    private String UPLOAD_PATH;

    @Autowired
    public CodelistImporterService(CodelistReaderLocator readerLocator, DocumentService documentService) {
        this.readerLocator = readerLocator;
        this.documentService = documentService;
    }

    public List<Document> read(CodelistReaderType readerType, File file) {
        if (readerType.equals(CodelistReaderType.SINGLE_TAB)) {
            return saveSingleTabDocument(file);
        }
        return saveMultiTabDocuments(file);
    }

    private List<Document> saveSingleTabDocument(File file) {
        final List<Document> documents = new LinkedList<>();
        try {
            final Document document = readSingleTabFile(file).orElseThrow();
            documents.add(documentService.save(document));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return documents;
    }

    private List<Document> saveMultiTabDocuments(File file) {
        try {
            return readMultiTabFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private Optional<Document> readSingleTabFile(File file) throws IOException {
        final InputStream stream = new FileInputStream(file);
        final CodelistReader reader = readerLocator.getReader(CodelistReaderType.SINGLE_TAB);

        final List<Document> documents = reader.readCodelist(stream);
        if (documents.size() > 0) {
            return Optional.of(documents.get(0));
        }
        return Optional.empty();
    }

    private List<Document> readMultiTabFile(File file) throws IOException {
        final InputStream stream = new FileInputStream(file);
        final CodelistReader reader = readerLocator.getReader(CodelistReaderType.MULTIPLE_TAB);

        return reader.readCodelist(stream);
    }
}
