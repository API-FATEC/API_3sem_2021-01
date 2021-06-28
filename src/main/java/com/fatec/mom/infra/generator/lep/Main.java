package com.fatec.mom.infra.generator.lep;

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public void main(int trait)
    {
        ClasseLEP classeLEP = new ClasseLEP(new ArrayList<ItensLEP>());
        if (trait == 50) {
            //new PrimeiroPDF("Testando o primeiro PDF");

            classeLEP.addItensLEP(new ItensLEP("0-TITLE", 00, 1, "REVISION 06"));
            classeLEP.addItensLEP(new ItensLEP("0-LEP", 01, 1, "REVISION 06"));

            //a estrutura de cada elemento da lista seria [secao, subsecao, numero, nome, codigo]
            //List<List<String>> data = new GetData().getData(documentName, partNumber, trait);
            //classeLEP.addItensLEP(new ItensLEP(data.get(2).get(0) + "-" + data.get(2).get(3), Integer.parseInt(data.get(2).get(4)), 3, "REVISION 06"));
            classeLEP.addItensLEP(new ItensLEP("0-LEP", 01, 2, "REVISION 06"));

            classeLEP.addItensLEP(new ItensLEP("0-LEP", 01, 3, "REVISION 06"));
            classeLEP.addItensLEP(new ItensLEP("0-LEP", 01, 4, "REVISION 06"));
            classeLEP.addItensLEP(new ItensLEP("0-LEP", 01, 5, "REVISION 06"));
            classeLEP.addItensLEP(new ItensLEP("0-TOC", 01, 1, "ORIGINAL"));
            classeLEP.addItensLEP(new ItensLEP("0-TOC", 01, 2, "ORIGINAL"));

            //classeLEP.addItensLEP(new ItensLEP(data.get(4).get(0) + "-" + data.get(4).get(2), Integer.parseInt(data.get(4).get(4)), 1, "REVISION 04"));
            classeLEP.addItensLEP(new ItensLEP("2-04", 01, 1, "REVISION 04"));

            classeLEP.addItensLEP(new ItensLEP("2-04", 01, 2, "REVISION 02"));
            classeLEP.addItensLEP(new ItensLEP("2-04", 01, 3, "REVISION 02"));
            classeLEP.addItensLEP(new ItensLEP("2-04", 01, 4, "REVISION 02"));
            classeLEP.addItensLEP(new ItensLEP("2-04", 01, 5, "REVISION 04"));
            classeLEP.addItensLEP(new ItensLEP("2-04", 01, 6, "ORIGINAL"));

            //classeLEP.addItensLEP(new ItensLEP(data.get(5).get(0) + "-" + data.get(5).get(1) + "-" + data.get(5).get(2), Integer.parseInt(data.get(0).get(4)), 1, "REVISION 05"));
            classeLEP.addItensLEP(new ItensLEP("3-01-03", 14, 1, "REVISION 05"));

            classeLEP.addItensLEP(new ItensLEP("3-01-03", 14, 2, "REVISION 02"));
            classeLEP.addItensLEP(new ItensLEP("3-01-03", 14, 3, "REVISION 05"));
            classeLEP.addItensLEP(new ItensLEP("3-01-03", 14, 4, "ORIGINAL"));
            classeLEP.addItensLEP(new ItensLEP("4-02", 02, 1, "REVISION 02"));
            classeLEP.addItensLEP(new ItensLEP("4-02", 02, 2, "REVISION 03"));
            classeLEP.addItensLEP(new ItensLEP("4-02", 02, 3, "REVISION 04"));
            classeLEP.addItensLEP(new ItensLEP("4-02", 02, 4, "REVISION 05"));
            classeLEP.addItensLEP(new ItensLEP("4-02", 02, 5, "REVISION 06"));
            classeLEP.addItensLEP(new ItensLEP("4-02", 02, 6, "REVISION 06"));
            classeLEP.addItensLEP(new ItensLEP("5-04-08", 12, 1, "REVISION 04"));
            classeLEP.addItensLEP(new ItensLEP("5-04-08", 12, 2, "ORIGINAL"));
            classeLEP.addItensLEP(new ItensLEP("5-04-08", 12, 3, "REVISION 04"));
            classeLEP.addItensLEP(new ItensLEP("5-04-08", 12, 4, "ORIGINAL"));
        }

        else if (trait == 55) {
            classeLEP.addItensLEP(new ItensLEP("0-TITLE", 00, 1, "REVISION 06"));
            classeLEP.addItensLEP(new ItensLEP("0-LEP", 02, 1, "REVISION 06"));
            classeLEP.addItensLEP(new ItensLEP("0-LEP", 02, 2, "REVISION 06"));
            classeLEP.addItensLEP(new ItensLEP("0-LEP", 02, 3, "REVISION 06"));
            classeLEP.addItensLEP(new ItensLEP("0-LEP", 02, 4, "REVISION 06"));
            classeLEP.addItensLEP(new ItensLEP("0-LEP", 02, 5, "REVISION 06"));
            classeLEP.addItensLEP(new ItensLEP("0-TOC", 02, 1, "ORIGINAL"));
            classeLEP.addItensLEP(new ItensLEP("0-TOC", 02, 2, "ORIGINAL"));
            classeLEP.addItensLEP(new ItensLEP("2-04", 02, 1, "REVISION 04"));
            classeLEP.addItensLEP(new ItensLEP("2-04", 02, 2, "REVISION 02"));
            classeLEP.addItensLEP(new ItensLEP("2-04", 02, 3, "REVISION 02"));
            classeLEP.addItensLEP(new ItensLEP("2-04", 02, 4, "REVISION 06"));
            classeLEP.addItensLEP(new ItensLEP("2-04", 02, 5, "REVISION 04"));
            classeLEP.addItensLEP(new ItensLEP("2-04", 02, 6, "ORIGINAL"));
            classeLEP.addItensLEP(new ItensLEP("3-01-03", 15, 1, "REVISION 03"));
            classeLEP.addItensLEP(new ItensLEP("3-01-03", 15, 2, "REVISION 04"));
            classeLEP.addItensLEP(new ItensLEP("3-01-03", 15, 3, "REVISION 05"));
            classeLEP.addItensLEP(new ItensLEP("3-01-03", 15, 4, "ORIGINAL"));
            classeLEP.addItensLEP(new ItensLEP("4-02", 03, 1, "REVISION 01"));
            classeLEP.addItensLEP(new ItensLEP("4-02", 03, 2, "REVISION 04"));
            classeLEP.addItensLEP(new ItensLEP("4-02", 03, 3, "REVISION 04"));
            classeLEP.addItensLEP(new ItensLEP("4-02", 03, 4, "REVISION 04"));
            classeLEP.addItensLEP(new ItensLEP("4-02", 03, 5, "REVISION 06"));
            classeLEP.addItensLEP(new ItensLEP("4-02", 03, 6, "REVISION 06"));
            classeLEP.addItensLEP(new ItensLEP("5-04-08", 12, 1, "REVISION 04"));
            classeLEP.addItensLEP(new ItensLEP("5-04-08", 12, 2, "ORIGINAL"));
            classeLEP.addItensLEP(new ItensLEP("5-04-08", 12, 3, "REVISION 04"));
            classeLEP.addItensLEP(new ItensLEP("5-04-08", 12, 4, "ORIGINAL"));
        }

        else if (trait == 60) {
            classeLEP.addItensLEP(new ItensLEP("0-TITLE", 00, 1, "REVISION 06"));
            classeLEP.addItensLEP(new ItensLEP("0-LEP", 03, 1, "REVISION 06"));
            classeLEP.addItensLEP(new ItensLEP("0-LEP", 03, 2, "REVISION 06"));
            classeLEP.addItensLEP(new ItensLEP("0-LEP", 03, 3, "REVISION 06"));
            classeLEP.addItensLEP(new ItensLEP("0-LEP", 03, 4, "REVISION 06"));
            classeLEP.addItensLEP(new ItensLEP("0-LEP", 03, 5, "REVISION 06"));
            classeLEP.addItensLEP(new ItensLEP("0-TOC", 01, 1, "ORIGINAL"));
            classeLEP.addItensLEP(new ItensLEP("0-TOC", 01, 2, "ORIGINAL"));
            classeLEP.addItensLEP(new ItensLEP("2-04", 03, 1, "REVISION 04"));
            classeLEP.addItensLEP(new ItensLEP("2-04", 03, 2, "REVISION 02"));
            classeLEP.addItensLEP(new ItensLEP("2-04", 03, 3, "REVISION 02"));
            classeLEP.addItensLEP(new ItensLEP("2-04", 03, 4, "REVISION 06"));
            classeLEP.addItensLEP(new ItensLEP("2-04", 03, 5, "REVISION 04"));
            classeLEP.addItensLEP(new ItensLEP("2-04", 03, 6, "ORIGINAL"));
            classeLEP.addItensLEP(new ItensLEP("3-01-03", 14, 1, "REVISION 03"));
            classeLEP.addItensLEP(new ItensLEP("3-01-03", 14, 2, "REVISION 02"));
            classeLEP.addItensLEP(new ItensLEP("3-01-03", 14, 3, "REVISION 05"));
            classeLEP.addItensLEP(new ItensLEP("3-01-03", 14, 4, "ORIGINAL"));
            classeLEP.addItensLEP(new ItensLEP("4-02", 01, 1, "REVISION 02"));
            classeLEP.addItensLEP(new ItensLEP("4-02", 01, 2, "REVISION 03"));
            classeLEP.addItensLEP(new ItensLEP("4-02", 01, 3, "REVISION 04"));
            classeLEP.addItensLEP(new ItensLEP("4-02", 01, 4, "REVISION 04"));
            classeLEP.addItensLEP(new ItensLEP("4-02", 01, 5, "REVISION 06"));
            classeLEP.addItensLEP(new ItensLEP("4-02", 01, 6, "REVISION 06"));
            classeLEP.addItensLEP(new ItensLEP("5-04-08", 12, 1, "REVISION 04"));
            classeLEP.addItensLEP(new ItensLEP("5-04-08", 12, 2, "ORIGINAL"));
            classeLEP.addItensLEP(new ItensLEP("5-04-08", 12, 3, "REVISION 04"));
            classeLEP.addItensLEP(new ItensLEP("5-04-08", 12, 4, "ORIGINAL"));

        }


        // Criando o relatorio
        Relatorio relatorioPDF = new RelatorioPDF(classeLEP, trait);
        relatorioPDF.gerarCabecalhoPaginaDeRevisao();
        relatorioPDF.gerarCorpoPaginaDeRevisao();
        relatorioPDF.gerarCabecalhoPaginaDosBlocos();
        relatorioPDF.gerarCorpoPaginaDosBlocos();
        relatorioPDF.imprimir();

    }
}