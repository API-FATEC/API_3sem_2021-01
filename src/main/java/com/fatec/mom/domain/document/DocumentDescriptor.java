package com.fatec.mom.domain.document;

import com.fatec.mom.domain.revision.Revision;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
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
