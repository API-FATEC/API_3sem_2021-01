package com.fatec.mom.infra.generator.full;

import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.revision.Revision;
import com.fatec.mom.domain.revision.RevisionStatus;
import com.fatec.mom.infra.exceptions.CheckoutRevisionException;
import com.fatec.mom.infra.generator.RevisionManipulator;
import com.fatec.mom.infra.generator.full.dataextractor.DocumentFilesExtractor;
import com.fatec.mom.infra.generator.pdf.PdfMerger;
import com.fatec.mom.infra.generator.pdf.PdfExtractor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Component
@Slf4j
public class FullDocumentGenerator {

    private static final String FULL_FORMAT = "%s-%d-%s-FULL.pdf";

    private final PdfMerger pdfMerger;

    //private final DocumentFilesExtractor documentFilesExtractor;

    private final PdfExtractor pdfExtractor;

    private final RevisionManipulator revisionManipulator;

    @Value("${default-documents-path}")
    private String documentsPath;

    @Autowired
    public FullDocumentGenerator(PdfMerger pdfMerger,
                                 //DocumentFilesExtractor documentFilesExtractor,
                                 RevisionManipulator revisionManipulator,
                                 PdfExtractor pdfExtractor) {
        this.pdfMerger = pdfMerger;
        //this.documentFilesExtractor = documentFilesExtractor;
        this.revisionManipulator = revisionManipulator;
        this.pdfExtractor = pdfExtractor;
    }

    public List<File> getFULL(final Revision revision) throws IOException {
        if (tryCheckout(revision)) {
            final var document = revision.getDocument();
            return validateFiles(document);
        } else  {
            throw new CheckoutRevisionException(String.format("Não foi possível obter os documents para a revisão %s", revision));
        }
    }

    private List<File> validateFiles(final Document document) {
        //final var documentFiles = documentFilesExtractor.extractFileNames(document);
        final var files = new LinkedList<File>();
        /*
        documentFiles.forEach((trait, fileNames) -> {
            final var pdfFiles = pdfExtractor.getInputStreamsFor(fileNames);

            try {
                files.add(pdfMerger.mergeFiles(pdfFiles, getFullDestinationFileName(document, trait)));
            } catch (IOException e) {
                e.printStackTrace();
                log.error("Não foi possível juntar os arquivos");
            }
        });
         */

        return files;
    }

    private boolean tryCheckout(final Revision revision) {
        revisionManipulator.checkoutToRevision(revision);
        return revisionManipulator.isIn(revision);
    }

    private String getFullDestinationFileName(final Document document, Integer trait) {
        return String.format("%s/%s",
                documentsPath,
                getFULLName(document, trait));
    }

    private String getFULLName(final Document document, final Integer trait) {
        final var lastRevision = document.getRevisions().stream()
                .filter(revision -> revision.getStatus().equals(RevisionStatus.FINISHED))
                .min(Comparator.comparing(Revision::getCreatedDate));

        if (lastRevision.isPresent()) {
            return String.format(FULL_FORMAT, document.getDocument(), trait, lastRevision.get().getName());
        }
        return String.format(FULL_FORMAT, document.getName(), trait, document.getPartNumber());
    }
}
