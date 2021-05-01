package com.fatec.mom.domain.codelist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Builder
@Getter
public class CodelistImportDescriptor {

    private final MultipartFile codelistFile;
    private final boolean isMultiTabSheet;
}
