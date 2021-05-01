package com.fatec.mom.infra.codelist.reader.config;

import com.fatec.mom.infra.codelist.reader.codelistBuilder.CodelistBuiderInvoker;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class CodelistConfig {

    @Getter
    @Autowired
    private final CodelistBuiderInvoker invoker;
}
