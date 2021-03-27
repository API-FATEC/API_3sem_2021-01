package com.fatec.mom.controller;

import com.fatec.mom.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/codelist")
public class FileController {

    @Autowired
    FileUploadService fileUploadService;

    @PostMapping
    public void uploadFile(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
    fileUploadService.uploadFile(file);
    }
}
