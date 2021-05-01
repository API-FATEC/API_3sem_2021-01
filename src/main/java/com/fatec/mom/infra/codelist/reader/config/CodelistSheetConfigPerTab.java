package com.fatec.mom.infra.codelist.reader.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@AllArgsConstructor
public class CodelistSheetConfigPerTab {

    private final int index;
    private final CodelistSheetConfig config;
}
