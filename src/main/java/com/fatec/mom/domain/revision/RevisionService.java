package com.fatec.mom.domain.revision;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RevisionService {

    @Autowired
    private RevisionRepository revisionRepository;

    @Transactional
    public Revision saveRev (Revision rev) {
        return revisionRepository.save(rev);
    }

    @Transactional
    public Revision findByName(final String name) {
        return revisionRepository.findByName(name).orElseThrow();
    }
}
