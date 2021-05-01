package com.fatec.mom.infra.codelist.reader.config;

import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class CodelistConfigLocator {

    private final Map<CodelistConfigType, CodelistConfig> configMap = new EnumMap<>(CodelistConfigType.class);

    public CodelistConfigLocator(Map<CodelistConfigType, CodelistConfig> configMap) {
        this.configMap.putAll(configMap);
    }

    public CodelistConfig getConfig(CodelistConfigType type) {
        return configMap.get(type);
    }
}
