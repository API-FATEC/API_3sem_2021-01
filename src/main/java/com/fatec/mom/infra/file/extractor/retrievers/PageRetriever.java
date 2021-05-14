package com.fatec.mom.infra.file.extractor.retrievers;

import com.fatec.mom.infra.file.extractor.domain.page.PageData;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.regex.Pattern;

@Component
public class PageRetriever extends DataRetriever {

    private static final Pattern pagePattern = Pattern.compile("page \\d{1,5}", Pattern.CASE_INSENSITIVE);

    @Override
    public PageData retrieveData(@NotNull String content) {
        String page = extractData(pagePattern, content);
        page = page.replaceFirst("(?i)page", "")
                .replace(" ", "");
        return new PageData(PageData.PageDataType.PAGE, page);
    }
}
