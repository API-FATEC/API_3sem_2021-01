package com.fatec.mom.infra.generator.full;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;
import java.io.IOException;

public class FullGenerator {

    public void getFull(String trait) throws IOException {
        //cria um File pra poder pegar o path do projeto
        File currentDirFile = new File(".");
        String helper = currentDirFile.getAbsolutePath();
        String currentDir = helper.substring(0, helper.length() - 1);
        System.out.println(currentDir);
        //usa o path do projeto e vai até a pasta Master do mockup
        String rootPath = currentDir + "doc\\Mockup FATEC\\MOCKUP\\ABC-1234\\Master\\";
        //path do desktop, para salvar no final
        File desktop = new File(System.getProperty("user.home"), "Desktop");

        //inicia o Merger
        PDFMergerUtility ut = new PDFMergerUtility();

        String[] inicial = {"00 Letter", "01 Cover", "02 List of Effective Pages", "03 Table of Contents"};

        if (trait.equals("50")){
            //vai pegar os arquivos de 00 Inicial e colocar no merge
            //no caso da Letter eu deixei fora do for pq ela é c50
            ut.addSource(rootPath + "00 Inicial\\" + inicial[0] + "\\ABC-1234-00-00c50.pdf");
            for (int i = 1; i < 4; i++){
                ut.addSource(rootPath + "00 Inicial\\" + inicial[i] + "\\ABC-1234-00-0" + i +"c01.pdf");
            }
            //pega os arquivos das outras seções
            ut.addSource(rootPath + "02 Story\\04 Introduction\\ABC-1234-02-04c01.pdf");
            ut.addSource(rootPath + "03 Chapter\\01 Temporada\\03 Episódio 02\\ABC-1234-03-01-03c14.pdf");
            ut.addSource(rootPath + "04 Middle\\02 Episódio 3\\ABC-1234-04-02c02.pdf");
            ut.addSource(rootPath + "05 General Data\\04 Temporada 6\\08 Episódio 1\\ABC-1234-05-04-08c12.pdf");
            ut.addSource(rootPath + "05 General Data\\06 Temporada 8\\03 Episódio 4\\ABC-1234-05-06-03c02.pdf");
            ut.addSource(rootPath + "AP01 Appendix\\02 Appendix\\ABC-1234-AP01-02c01.pdf");
            ut.addSource(rootPath + "S03 Supplement\\05 Mars\\ABC-1234-S03-05c01.pdf");
            ut.addSource(rootPath + "S03 Supplement\\10 Copyright\\ABC-1234-S03-10c01.pdf");
            //especifica o local e o nome
            ut.setDestinationFileName(desktop.getPath() + "\\[]ABC-1234-50-FULL.pdf");
        }

        //faz a mesma coisa mas selecionando os blocos q fazem parte do traço 55 e depois 60
        else if (trait.equals("55")){
            ut.addSource(rootPath + "00 Inicial\\" + inicial[0] + "\\ABC-1234-00-00c55.pdf");
            for (int i = 1; i < 3; i++){
                ut.addSource(rootPath + "00 Inicial\\" + inicial[i] + "\\ABC-1234-00-0" + i +"c02.pdf");
            }
            ut.addSource(rootPath + "00 Inicial\\" + inicial[3] + "\\ABC-1234-00-03c01.pdf");
            ut.addSource(rootPath + "02 Story\\04 Introduction\\ABC-1234-02-04c02.pdf");
            ut.addSource(rootPath + "03 Chapter\\01 Temporada\\03 Episódio 02\\ABC-1234-03-01-03c15.pdf");
            ut.addSource(rootPath + "04 Middle\\02 Episódio 3\\ABC-1234-04-02c03.pdf");
            ut.addSource(rootPath + "05 General Data\\04 Temporada 6\\08 Episódio 1\\ABC-1234-05-04-08c12.pdf");
            ut.addSource(rootPath + "AP01 Appendix\\02 Appendix\\ABC-1234-AP01-02c01.pdf");
            ut.addSource(rootPath + "S03 Supplement\\05 Mars\\ABC-1234-S03-05c01.pdf");
            ut.addSource(rootPath + "S03 Supplement\\10 Copyright\\ABC-1234-S03-10c01.pdf");

            ut.setDestinationFileName(desktop.getPath() + "\\[]ABC-1234-55-FULL.pdf");
        }

        else if (trait.equals("60")){
            ut.addSource(rootPath + "00 Inicial\\" + inicial[0] + "\\ABC-1234-00-00c60.pdf");
            for (int i = 1; i < 3; i++){
                ut.addSource(rootPath + "00 Inicial\\" + inicial[i] + "\\ABC-1234-00-0" + i +"c03.pdf");
            }
            ut.addSource(rootPath + "00 Inicial\\" + inicial[3] + "\\ABC-1234-00-03c01.pdf");
            ut.addSource(rootPath + "02 Story\\04 Introduction\\ABC-1234-02-04c03.pdf");
            ut.addSource(rootPath + "03 Chapter\\01 Temporada\\03 Episódio 02\\ABC-1234-03-01-03c14.pdf");
            ut.addSource(rootPath + "04 Middle\\02 Episódio 3\\ABC-1234-04-02c01.pdf");
            ut.addSource(rootPath + "05 General Data\\04 Temporada 6\\08 Episódio 1\\ABC-1234-05-04-08c12.pdf");
            ut.addSource(rootPath + "05 General Data\\06 Temporada 8\\03 Episódio 4\\ABC-1234-05-06-03c01.pdf");
            ut.addSource(rootPath + "AP01 Appendix\\02 Appendix\\ABC-1234-AP01-02c01.pdf");
            ut.addSource(rootPath + "S03 Supplement\\05 Mars\\ABC-1234-S03-05c01.pdf");
            ut.addSource(rootPath + "S03 Supplement\\10 Copyright\\ABC-1234-S03-10c02.pdf");

            ut.setDestinationFileName(desktop.getPath() + "\\[]ABC-1234-60-FULL.pdf");
        }
        ut.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
    }
}
