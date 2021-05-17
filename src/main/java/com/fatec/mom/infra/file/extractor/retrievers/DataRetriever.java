package com.fatec.mom.infra.file.extractor.retrievers;

import com.fatec.mom.infra.file.extractor.domain.page.PageData;

import javax.validation.constraints.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class DataRetriever {

        protected static final String DEFAULT_VALUE = "";

        public abstract PageData retrieveData(@NotNull final String content);

        protected String extractData(@NotNull Pattern pattern, @NotNull final String content) {
                Matcher matcher = pattern.matcher(content);
                return matcher.find() ? matcher.group() : DEFAULT_VALUE;
        }
}
