package com.fatec.mom.domain.document;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DocumentDescriptor {

    private final String documentPath;
    private final Document document;

    public static DocumentDescriptor.Factory factory() {
        return new Factory();
    }

    public static class Factory {

        private static final String DEFAULT_PATH = "D:/Documents/facul/PI_III/tests/repository_test/%s";

        public DocumentDescriptor createDescriptor(Document document) {
            return new DocumentDescriptor(String.format(DEFAULT_PATH, document.getDocument()), document);
        }
    }
}
