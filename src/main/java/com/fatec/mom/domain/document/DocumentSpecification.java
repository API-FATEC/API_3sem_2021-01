package com.fatec.mom.domain.document;

import org.springframework.data.jpa.domain.Specification;

public class DocumentSpecification {

    public static Specification<Document> searchByName(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name") , "%" + name + "%");
    }
}
