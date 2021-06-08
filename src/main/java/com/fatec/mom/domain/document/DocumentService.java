package com.fatec.mom.domain.document;

import com.fatec.mom.domain.revision.Revision;
import com.fatec.mom.domain.revision.RevisionService;
import com.fatec.mom.infra.exceptions.ItemNotFoundException;
import com.fatec.mom.infra.generator.DocumentGeneratorRetriever;
import com.fatec.mom.infra.generator.full.FullDocumentGenerator;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class DocumentService {

    private static final String PDF_TYPE = "application/pdf";

    private final DocumentRepository documentRepository;

    private final RevisionService revisionService;

    private final DocumentGeneratorRetriever generatorRetriever;

    @Transactional
    public List<Document> findAll() {
        final var documents = documentRepository.findAll();
        if (documents == null || documents.isEmpty()) {
            throw new ItemNotFoundException("Nenhum documento cadastrado no sistema");
        }
        return documents;
    }

    @Transactional
    public Document findByNameAndPartNumber(final String name, final Integer partNumber) {
        final var documents = documentRepository.findAll(DocumentSpecification.searchByName(name));
        if (documents.isEmpty()) {
            throw new ItemNotFoundException(String.format("Não foi encontrado nenhum documento com nome %s e partNumber %d", name, partNumber));
        }
        return documents.stream()
                .filter(document -> document.getPartNumber().equals(partNumber))
                .findFirst().orElseThrow();
    }

    @Transactional
    public Set<Document> findAllByNameAndPartNumberAndTrait(final String name, final Integer partNumber, Integer trait) {
        final var docs = documentRepository.findAll(DocumentSpecification.searchByName(name));
        if (docs.isEmpty()) {
            throw new ItemNotFoundException(String.format("Não foi encontrado nenhum documento com nome %s, partNumber %d e traço %d", name, partNumber, trait));
        }
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
            final var generator = generatorRetriever.getGenerator(DocumentType.FULL);
            final var full = generator.generate(revision);
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
}
