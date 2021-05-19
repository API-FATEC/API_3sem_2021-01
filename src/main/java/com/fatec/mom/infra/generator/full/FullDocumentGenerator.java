package com.fatec.mom.infra.generator.full;

import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.revision.Revision;
import com.fatec.mom.domain.revision.RevisionStatus;
import com.fatec.mom.infra.generator.full.dataextractor.DocumentFilesExtractor;
import com.fatec.mom.infra.generator.pdf.PdfMerger;
import com.fatec.mom.infra.generator.pdf.PdfExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;

@Component
public class FullDocumentGenerator {

    private static final String FULL_FORMAT = "%s-%s-FULL.pdf";

    private final PdfMerger pdfMerger;

    private final DocumentFilesExtractor documentFilesExtractor;

    private final PdfExtractor pdfExtractor;

    @Value("${default-documents-path}")
    private String documentsPath;

    @Autowired
    public FullDocumentGenerator(PdfMerger pdfMerger,
                                 DocumentFilesExtractor documentFilesExtractor,
                                 PdfExtractor pdfExtractor) {
        this.pdfMerger = pdfMerger;
        this.documentFilesExtractor = documentFilesExtractor;
        this.pdfExtractor = pdfExtractor;
    }

    public File getFULL(final Document document) throws IOException {
        final var documentFiles = documentFilesExtractor.extractFileNames(document);
        final var pdfFiles = pdfExtractor.getInputStreamsFor(documentFiles);
        return pdfMerger.mergeFiles(pdfFiles, getFullDestinationFileName(document));
    }

    private String getFullDestinationFileName(final Document document) {
        return String.format("%s/%s",
                documentsPath,
                getFULLName(document));
    }

    private String getFULLName(final Document document) {
        final var lastRevision = document.getRevisions().stream()
                .filter(revision -> revision.getStatus().equals(RevisionStatus.FINISHED))
                .min(Comparator.comparing(Revision::getCreatedDate));

        if (lastRevision.isPresent()) {
            return String.format(FULL_FORMAT, document.getDocument(), lastRevision.get().getName());
        }
        return String.format(FULL_FORMAT, document.getName(), document.getPartNumber());
    }
}
