package com.fatec.mom.infra.file.extractor.retrievers;

import com.fatec.mom.infra.file.extractor.domain.page.PageFooter;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

@Component
public class FooterRetriever {

    private static final float X = 0F;
    private static final float Y = 570F;
    private static final float WEIGHT = 400F;
    private static final float HEIGHT = 60F;
    private static final String FOOTER = "footer_data";

    public PageFooter extractFooter(@NotNull final PDPage page) throws IOException {
        PDFTextStripperByArea textStripper = new PDFTextStripperByArea();
        Rectangle2D rectangle = new Rectangle2D.Float(X, Y, WEIGHT, HEIGHT);

        textStripper.setSortByPosition(true);
        textStripper.addRegion(FOOTER, rectangle);
        textStripper.extractRegions(page);

        return new PageFooter(textStripper.getTextForRegion(FOOTER));
    }
}
