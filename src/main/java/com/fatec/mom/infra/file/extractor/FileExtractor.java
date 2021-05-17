package com.fatec.mom.infra.file.extractor;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.block.BlockPage;
import com.fatec.mom.infra.file.extractor.domain.page.PageContentsHolder;
import com.fatec.mom.infra.file.extractor.pagebuilder.PageBuilderInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class FileExtractor {

    private final PageReader pageReader;

    private final PageBuilderInvoker pageBuilderInvoker;

    @Autowired
    public FileExtractor(PageReader pageReader,
                         PageBuilderInvoker pageBuilderInvoker) {
        this.pageReader = pageReader;
        this.pageBuilderInvoker = pageBuilderInvoker;
    }

    public List<BlockPage> extractPages(final Block block, @NotNull InputStream inputStream) {
        final PageContentsHolder contentsHolder = new PageContentsHolder();
        try {
            contentsHolder.setContents(pageReader.readPages(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pageBuilderInvoker.assemblePages(block, contentsHolder);
    }
}
