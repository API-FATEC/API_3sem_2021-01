package com.fatec.mom.infra.generator.lep;

import com.fatec.mom.domain.lep.LEP;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Optional;

@Component
public class LEPGenerator {

    public Optional<File> generateFrom(final LEP lep) {
        return Optional.empty();
    }
}
