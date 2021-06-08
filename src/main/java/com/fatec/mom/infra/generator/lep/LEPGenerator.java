package com.fatec.mom.infra.generator.lep;

import com.fatec.mom.infra.generator.FileGenerator;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Component
public class LEPGenerator implements FileGenerator {

    public Optional<InputStream> generateFrom(final List<InputStream> files) {
        return Optional.empty();
    }

    @Override
    public InputStream generate() {
        return null;
    }
}
