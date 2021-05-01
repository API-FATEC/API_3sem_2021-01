package com.fatec.mom.domain.document;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>, JpaSpecificationExecutor<Document> {

    @Override
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "blocks")
    List<Document> findAll();

    @Override
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "blocks")
    <S extends Document> List<S> saveAll(Iterable<S> iterable);

    @Override
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "blocks")
    <S extends Document> S save(S s);
}
