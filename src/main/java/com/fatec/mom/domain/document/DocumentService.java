package com.fatec.mom.domain.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Transactional
    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    @Transactional
    public List<Document> search(String text) {
        return documentRepository.findAll(
                DocumentSpecification.searchByName(text)
            .or(DocumentSpecification.searchByPartNumber(text))
            .or(DocumentSpecification.searchByTrait(text)));
    }

    @Transactional
    public List<Document> searchByName(String name) {
        return documentRepository.findAll(DocumentSpecification.searchByName(name));
    }

    @Transactional
    public Document save(final Document document) {
        return documentRepository.save(document);
    }

    @Transactional
    public List<Document> saveAll(Iterable<Document> documents) {
        return documentRepository.saveAll(documents);
    }

}
