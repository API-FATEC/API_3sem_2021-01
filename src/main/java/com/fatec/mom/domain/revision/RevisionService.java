package com.fatec.mom.domain.revision;

import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.document.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RevisionService {

    @Autowired
    private RevisionRepository revisionRepository;

    @Autowired
    private DocumentService documentService;

    @Transactional
    public Revision saveRev(Revision rev) {
        return revisionRepository.save(rev);
    }

    @Transactional
    public Revision findByName(final String name) {
        return revisionRepository.findByName(name).orElseThrow();
    }

    @Transactional
    public Optional<Revision> findOpened(final Long documentId) {
        final var document = documentService.findById(documentId);
        if (document.isPresent()) {
            return revisionRepository.findByDocumentAndStatus(document.get(), RevisionStatus.OPENED);
        }

        return Optional.empty();
    }
}
