package com.fatec.mom.infra.generator.validators;

import java.util.List;

public interface DocumentGeneratorValidator {

    void validateName(final String fileName) throws RuntimeException;
}
