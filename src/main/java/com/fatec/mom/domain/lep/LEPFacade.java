package com.fatec.mom.domain.lep;

import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.revision.Revision;
import com.fatec.mom.domain.trait.Trait;
import com.fatec.mom.infra.exceptions.EmptyFileListException;
import com.fatec.mom.infra.exceptions.LepGenerationException;
import com.fatec.mom.infra.generator.lep.LEPGenerator;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import java.io.InputStream;

@Component
@AllArgsConstructor
public class LEPFacade {

    private final LEPService lepService;

    private final LEPGenerator lepGenerator;

    public InputStreamResource generate(final Revision revision, final Trait trait) {
        final var document = revision.getDocument();
        return generateFrom(document, trait);
    }

    public InputStreamResource generateFrom(final Document document, final Trait trait) {
        final var files = lepService.getFilesByTrait(document, trait);

        if (files.isEmpty()) {
            throw new EmptyFileListException("Não foi encontrado nenhum arquivo");
        }

        final InputStream lep = lepGenerator.generateFrom(files)
                .orElseThrow(() -> new LepGenerationException("Não foi possível gerar a LEP"));

        return new InputStreamResource(lep);
    }
}
