package com.fatec.mom.domain.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UploadedFileService {

    @Autowired
    private UploadedFileRepository uploadedFileRepository;

    @Transactional
    public UploadedFile save(UploadedFile file) {
        return uploadedFileRepository.save(file);
    }
}
