package com.fatec.mom.domain.codelist;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentService;
import com.fatec.mom.domain.file.FileUploadService;
import com.fatec.mom.domain.trait.Trait;
import com.fatec.mom.infra.codelist.reader.CodelistReaderType;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class CodelistService {

    public static final String SHEET_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    private final DocumentService documentService;

    private final CodelistImporterService codelistImporterService;

    private final FileUploadService fileUploadService;

    @Autowired
    public CodelistService(DocumentService documentService,
                           CodelistImporterService codelistImporterService,
                           FileUploadService fileUploadService) {
        this.documentService = documentService;
        this.codelistImporterService = codelistImporterService;
        this.fileUploadService = fileUploadService;
    }

    public List<Document> handleImport(@NotNull final List<MultipartFile> files) throws IOException {
        final List<Document> documents = new LinkedList<>();

        for (MultipartFile file : files) {
            var importedDocs = handleImport(file);
            documents.addAll(importedDocs);
        }

        return documents;
    }

    private List<Document> handleImport(@NotNull final MultipartFile multipartFile) throws IOException {
        if (isExcel(multipartFile)) {
            File savedFile = fileUploadService.uploadFile(multipartFile);
            return importCodelist(savedFile);
        }
        log.info(String.format("The file %s is not a sheet file.", multipartFile.getOriginalFilename()));
        return Collections.emptyList();
    }

    public List<Document> importCodelist(@NotNull final File file) {
        final List<Document> documents = codelistImporterService.read(CodelistReaderType.MULTIPLE_TAB, file);

        if (documents != null) {
            return documentService.saveAll(documents);
        }
        return Collections.emptyList();
    }

    private boolean isExcel(@NotNull final MultipartFile file) {
        return Objects.requireNonNull(file.getContentType()).equalsIgnoreCase(SHEET_TYPE);
    }

    private List<Integer> calculateChecklist(final Set<Trait> traits, final Block block) {
        var checklist = new LinkedList<Integer>();
        traits.forEach(trait -> checklist.add(block.hasTrait(trait) ? 1 : 0));
        return checklist;
    }

    public Codelist findCodelist(String name, Integer partNumber) {
        final Document doc = documentService.findByNameAndPartNumber(name, partNumber);

        return Codelist.builder()
                .document(doc)
                .codelistBlocks(getAllCodelistBlocks(doc.getBlocks(), doc.getTraits()))
                .build();
    }

    private Set<CodelistBlock> getAllCodelistBlocks(final Set<Block> blocks, final Set<Trait> traits) {
        var codelistBlocks = new HashSet<CodelistBlock>();
        blocks.forEach(block -> codelistBlocks.add(CodelistBlock.createCodelistBlock(block)));
        codelistBlocks.forEach(codelistBlock -> codelistBlock.setChecklist(calculateChecklist(traits, codelistBlock.getBlock())));
        return codelistBlocks;
    }
}
