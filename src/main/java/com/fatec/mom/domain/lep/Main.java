package com.fatec.mom.domain.lep;

import com.fatec.mom.domain.lep.ClasseLEP;

import java.util.ArrayList;

public class Main
{
    public static void main( String[] args )
    {
        //new PrimeiroPDF("Testando o primeiro PDF");

        ClasseLEP classeLEP = new ClasseLEP(new ArrayList<ItensLEP>());
        classeLEP.addItensLEP(new ItensLEP("0-TITLE", 00, 1, "REVISION 06"));
        classeLEP.addItensLEP(new ItensLEP("0-LEP", 01, 2, "REVISION 06"));
        classeLEP.addItensLEP(new ItensLEP("0-LEP", 01, 3, "REVISION 06"));
        classeLEP.addItensLEP(new ItensLEP("0-LEP", 01, 4, "REVISION 06"));
        classeLEP.addItensLEP(new ItensLEP("0-LEP", 01, 5, "REVISION 06"));
        classeLEP.addItensLEP(new ItensLEP("0-LEP", 01, 6, "REVISION 06"));
        classeLEP.addItensLEP(new ItensLEP("0-TOC", 01, 1, "ORIGINAL"));
        classeLEP.addItensLEP(new ItensLEP("0-TOC", 01, 2, "ORIGINAL"));
        classeLEP.addItensLEP(new ItensLEP("2-04", 01, 1, "REVISION 04"));
        classeLEP.addItensLEP(new ItensLEP("2-04", 01, 2, "REVISION 02"));
        classeLEP.addItensLEP(new ItensLEP("2-04", 01, 3, "REVISION 02"));
        classeLEP.addItensLEP(new ItensLEP("2-04", 01, 4, "REVISION 02"));
        classeLEP.addItensLEP(new ItensLEP("2-04", 01, 5, "REVISION 04"));
        classeLEP.addItensLEP(new ItensLEP("2-04", 01, 6, "ORIGINAL"));
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

        // Criando o relatorio
        Relatorio relatorioPDF = new RelatorioPDF(classeLEP);
        relatorioPDF.gerarCabecalhoPaginaDeRevisao();
        relatorioPDF.gerarCorpoPaginaDeRevisao();
        relatorioPDF.gerarCabecalhoPaginaDosBlocos();
        relatorioPDF.gerarCorpoPaginaDosBlocos();
        relatorioPDF.imprimir();

    }
}