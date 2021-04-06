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
    public List<Document> saveAll(Iterable<Document> documents) {
        return documentRepository.saveAll(documents);
    }

    @Transactional
    public List<Document> findAllByNameAndPartNumberAndTrait(String name, Integer partNumber, Integer trait) {
        return documentRepository.findAllByNameAndPartNumberAndTrait(name, partNumber, trait);
    }

    @Transactional
    public List<Document> findAllByNameAndPartNumber(final String name, final Integer partNumber) {
        return documentRepository.findAllByNameAndPartNumber(name, partNumber);
    }
}
