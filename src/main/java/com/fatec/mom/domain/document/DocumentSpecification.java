package com.fatec.mom.domain.document;

import org.springframework.data.jpa.domain.Specification;

public class DocumentSpecification {

    private DocumentSpecification() {}

    public static Specification<Document> searchByName(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("name")) , "%" + name.toLowerCase() + "%");
    }

    public static Specification<Document> searchByPartNumber(Integer pn) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("partNumber") , pn);
    }

    public static Specification<Document> searchByTrait(Integer trait) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("trait") ,  trait);
    }
}
