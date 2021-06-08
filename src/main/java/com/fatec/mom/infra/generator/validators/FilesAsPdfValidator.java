package com.fatec.mom.infra.generator.validators;

import com.fatec.mom.infra.exceptions.InvalidFileTypeException;
import org.springframework.stereotype.Component;

@Component
public class FilesAsPdfValidator implements DocumentGeneratorValidator {

    private static final String PDF = "pdf";

    @Override
    public void validateName(String fileName) throws RuntimeException {
        if (!fileName.toLowerCase().contains(PDF)) {
            throw new InvalidFileTypeException(String.format("O arquivo %s não é um PDF", fileName));
        }
    }
}
