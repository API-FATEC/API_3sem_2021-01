package com.fatec.mom.domain.lep;

import com.fatec.mom.domain.lep.ClasseLEP;

public class Main
{
    public static void main( String[] args )
    {
        //new PrimeiroPDF("Testando o primeiro PDF");

        ClasseLEP classeLEP = new ClasseLEP("0-TITLE", 00, 1, "REVISION 7");

        // Criando o relatorio
        Relatorio relatorioPDF = new RelatorioPDF(classeLEP);
        //Relatorio relatorioPdfSimples = new RelatorioPDFBonito(venda);
        relatorioPDF.gerarCabecalho();
        //relatorioPdfSimples.gerarCorpo();
        //relatorioPdfSimples.gerarRodape();
        relatorioPDF.imprimir();

    }
}