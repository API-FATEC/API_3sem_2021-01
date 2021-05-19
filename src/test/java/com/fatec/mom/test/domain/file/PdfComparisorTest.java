package com.fatec.mom.test.domain.file;

import com.fatec.mom.test.integration.IntegrationTest;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@IntegrationTest
public class PdfComparisorTest {

    @Test
    @DisplayName("Compara dois arquivos pdf")
    void comparaDoisArquivosPdf() throws IOException {
        final var file1 = "ABC-1234-55-REV6-FULL.pdf";
        final var file2 = "ABC-1234-55-REV7-FULL.pdf";
        var inputStream1 = getClass().getResourceAsStream(String.format("/com/fatec/mom/test/documents/%s", file1));
        var inputStream2 = getClass().getResourceAsStream(String.format("/com/fatec/mom/test/documents/%s", file2));

        PDDocument document1 = PDDocument.load(inputStream1);
        PDDocument document2 = PDDocument.load(inputStream2);

        final PDPageTree pages1 = document1.getPages();
        final PDPageTree pages2 = document2.getPages();

        var splitter1 = new Splitter();
        splitter1.setStartPage(1);
        splitter1.setEndPage(document1.getNumberOfPages());

        final var splitted1 = splitter1.split(document1);

        var splitter2 = new Splitter();
        splitter2.setStartPage(1);
        splitter2.setEndPage(document1.getNumberOfPages());
        splitter2.setEndPage(document2.getNumberOfPages());
        final var splitted2 = splitter2.split(document2);


        for (int i = 0; i < document2.getNumberOfPages(); ++i) {
            final var doc1 = splitted1.get(i);
            final var doc2 = splitted2.get(i);

            System.out.println(doc1.getDocumentCatalog().getVersion());
            System.out.println(doc2.getDocumentCatalog().getVersion());
        }

        System.out.println();
    }
}
