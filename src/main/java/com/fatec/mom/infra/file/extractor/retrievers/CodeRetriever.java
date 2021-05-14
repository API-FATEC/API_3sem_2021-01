package com.fatec.mom.infra.file.extractor.retrievers;

import com.fatec.mom.infra.file.extractor.domain.page.PageData;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.regex.Pattern;

@Component
public class CodeRetriever extends DataRetriever {

    private static final Pattern codePattern = Pattern.compile("code \\d{1,5}", Pattern.CASE_INSENSITIVE);

    @Override
    public PageData retrieveData(@NotNull String content) {
        String code = extractData(codePattern, content);
        code = code.replaceFirst("(?i)code", "")
                .replace(" ", "");
        return new PageData(PageData.PageDataType.CODE, code);
    }
}
