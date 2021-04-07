package com.fatec.mom.domain.document;

import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor
public class DocumentSpecification {

    public static Specification<Document> searchByName(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name") , "%" + name + "%");
    }

    public static Specification<Document> searchByPartNumber(Integer pn) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("partNumber") , pn);
    }

    public static Specification<Document> searchByTrait(Integer trait) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("trait") ,  trait);
    }
}
