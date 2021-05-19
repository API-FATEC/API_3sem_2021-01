package com.fatec.mom.infra.generator.pdf;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
@Slf4j
public class PdfMerger {

    public File mergeFiles(final List<InputStream> streams, final String destinationFileName) throws IOException {
        PDFMergerUtility mergerUtility = new PDFMergerUtility();

        log.info("Adicionando todos os arquivos para o merge");
        streams.forEach(mergerUtility::addSource);

        mergerUtility.setDestinationFileName(destinationFileName);
        mergerUtility.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());

        return new File(destinationFileName);
    }
}
