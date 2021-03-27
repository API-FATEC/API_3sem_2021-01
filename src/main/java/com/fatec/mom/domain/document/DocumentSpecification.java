package com.fatec.mom.domain.document;

import org.springframework.data.jpa.domain.Specification;

public class DocumentSpecification {

    public static Specification<Document> searchByName(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name") , "%" + name + "%");
    }

    public static Specification<Document> searchByPartNumber(String pn) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("partNumber") , "%" + pn + "%");
    }

    public static Specification<Document> searchByTrait(String trait) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name") , "%" + trait + "%");
    }
}
