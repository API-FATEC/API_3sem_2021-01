package com.fatec.mom.test.infra.generator;

import com.fatec.mom.infra.generator.full.FullGenerator;
import com.fatec.mom.test.integration.IntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

@IntegrationTest
public class FullDocumentGeneratorTest {

    @Test
    @DisplayName("Tries to create the Full pdf files")
    void generateFullDocument() throws IOException {
        //inicializa a classe e executa o método
        var generator = new FullGenerator();
        generator.getFull("50");
        System.out.println("50 Gerado com sucesso");
        generator.getFull("55");
        System.out.println("55 Gerado com sucesso");
        generator.getFull("60");
        System.out.println("60 Gerado com sucesso");

        System.out.println("--------------");

        //caso tenha gerado com sucesso o arquivo será apagado,
        //o objetivo do teste é apenas verificar se está gerando corretamente
        File desktop = new File(System.getProperty("user.home"), "Desktop");
        File full50 = new File(desktop.getPath() + "\\[]ABC-1234-50-FULL.pdf");
        File full55 = new File(desktop.getPath() + "\\[]ABC-1234-55-FULL.pdf");
        File full60 = new File(desktop.getPath() + "\\[]ABC-1234-60-FULL.pdf");
        full50.delete();full55.delete();full60.delete();
        System.out.println("Arquivos apagados.\nTeste concluido com sucesso.");
    }

}
