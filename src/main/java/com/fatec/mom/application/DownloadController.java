package com.fatec.mom.application;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class DownloadController {

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public HttpEntity<byte[]> download() throws IOException {

        byte[] arquivo = Files.readAllBytes( Paths.get("C:/Users/tairi/Desktop/[]ABC-1234-60-FULL.pdf") );

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Content-Disposition", "attachment;filename=\"[]ABC-1234-60-FULL.pdf\"");

        HttpEntity<byte[]> entity = new HttpEntity<byte[]>( arquivo, httpHeaders);

        return entity;
    }

}
