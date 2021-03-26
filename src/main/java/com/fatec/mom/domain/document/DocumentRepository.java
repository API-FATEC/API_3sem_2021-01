package com.fatec.mom.domain.document;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findAllByName(String name);

    List<Document> findAllByNameAndPartNumber(String name, Integer partNumber);

    List<Document> findAllByNameAndPartNumberAndTrait(String name, Integer partNumber, Integer trait);
}
