package com.fatec.mom.infra.generator.pdf;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class PdfExtractor {

    private static final String PDF = "pdf";

    public List<InputStream> getInputStreamsFor(final List<String> files) {
        final var fileInputStreams = new LinkedList<InputStream>();

        files.forEach(fileName -> {
            try {
                final var inputStreamsInsidePath = getPDFs(fileName);
                fileInputStreams.addAll(inputStreamsInsidePath);
            } catch (FileNotFoundException e) {
                log.error(String.format("Arquivo %s não encontrado", fileName));
            }
        });

        return fileInputStreams;
    }

    private List<InputStream> getPDFs(final String filePath) throws FileNotFoundException {
        final var inputStreams = new LinkedList<InputStream>();

        final var path = new File(filePath);
        final var filesInsidePath = Optional.ofNullable(path.listFiles());

        if (filesInsidePath.isPresent()) {
            final var files = filesInsidePath.get();
            for (var file : files) {
                if (FilenameUtils.getExtension(file.getName()).equalsIgnoreCase(PDF)) {
                    final var inputStream = new FileInputStream(file);
                    inputStreams.add(inputStream);
                }
            }
        }

        return inputStreams;
    }
}
