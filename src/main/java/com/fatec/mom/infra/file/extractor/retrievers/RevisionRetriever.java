package com.fatec.mom.infra.file.extractor.retrievers;

import com.fatec.mom.infra.file.extractor.domain.page.PageData;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.regex.Pattern;

@Component
public class RevisionRetriever extends DataRetriever {

    private static final Pattern revisionPattern = Pattern.compile("(revision \\d{1,5}|original)", Pattern.CASE_INSENSITIVE);

    @Override
    public PageData retrieveData(@NotNull String content) {
        String revision = extractData(revisionPattern, content);
        return new PageData(PageData.PageDataType.REVISION, revision);
    }
}
