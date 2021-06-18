package com.fatec.mom.domain.document;

import com.fatec.mom.domain.revision.Revision;
import com.fatec.mom.domain.revision.RevisionService;
import com.fatec.mom.infra.generator.full.FullDocumentGenerator;
import com.fatec.mom.infra.generator.full.FullGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    private static final String PDF_TYPE = "application/pdf";

    private final DocumentRepository documentRepository;

    private final RevisionService revisionService;

    private final FullDocumentGenerator fullDocumentGenerator;

    @Autowired
    public DocumentService(DocumentRepository documentRepository,
                           RevisionService revisionService,
                           FullDocumentGenerator fullDocumentGenerator) {
        this.documentRepository = documentRepository;
        this.revisionService = revisionService;
        this.fullDocumentGenerator = fullDocumentGenerator;
    }

    @Transactional
    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    @Transactional
    public Document findByNameAndPartNumber(final String name, final Integer partNumber) {
        final var docs = documentRepository.findAll(DocumentSpecification.searchByName(name));
        return docs.stream()
                .filter(document -> document.getPartNumber().equals(partNumber))
                .findFirst().orElseThrow();
    }

    @Transactional
    public Set<Document> findAllByNameAndPartNumberAndTrait(final String name, final Integer partNumber, Integer trait) {
        final var docs = documentRepository.findAll(DocumentSpecification.searchByName(name));
        return docs.stream()
                .filter(document -> document.getPartNumber().equals(partNumber))
                .filter(document -> document.containsTrait(trait))
                .collect(Collectors.toSet());
    }

    @Transactional
    public List<Document> saveAll(final List<Document> documents) {
        return documents.stream().map(documentRepository::save).collect(Collectors.toList());
    }

    @Transactional
    public Set<Integer> findAllPartNumbersByName(final String name) {
        var docs = documentRepository.findAll(DocumentSpecification.searchByName(name));
        return docs.stream().map(Document::getPartNumber).collect(Collectors.toSet());
    }

    @Transactional
    public Set<String> findAllNames() {
        var docs = documentRepository.findAll();
        return docs.stream().map(Document::getName).collect(Collectors.toSet());
    }

    @Transactional
    public Document save(Document document) {
        return documentRepository.save(document);
    }

    @Transactional
    public Optional<Document> findById(final Long id) {
        return documentRepository.findById(id);
    }

    @Transactional
    public Optional<InputStreamResource> generateFULL(final Revision revision) {
        try {
            final var full = fullDocumentGenerator.getFULL(revision);
            //return Optional.of(getFileResponse(full));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public InputStreamResource getFileResponse(final File file) throws FileNotFoundException {
        final var inputStream = new FileInputStream(file);
        return new InputStreamResource(inputStream);
    }

    public Document importDocument(final Long id) {
        final var document = documentRepository.findById(id);
        return document.orElseThrow();
    }

    public void downloadFullNew(String trait) throws IOException {
        //inicializa um FullGenerator e executa o metodo q gera o FULL, de acordo com o trait passado
        var generator = new FullGenerator();
        generator.getFull(trait);
    }
}
