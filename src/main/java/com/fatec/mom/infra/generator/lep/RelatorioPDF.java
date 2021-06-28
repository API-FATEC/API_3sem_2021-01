package com.fatec.mom.infra.generator.lep;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class RelatorioPDF implements Relatorio{
    private ClasseLEP classeLEP;
    private Document documentoPDF;
    private String caminhoRelatorio = "D:/LEP.pdf";

    public RelatorioPDF(ClasseLEP classeLEP, int trait) {

        File currentDirFile = new File(".");
        String helper = currentDirFile.getAbsolutePath();
        String currentDir = helper.substring(0, helper.length() - 1);
        String rootPath = currentDir + "doc\\Mockup FATEC\\MOCKUP\\ABC-1234\\Master\\00 Inicial\\02 List of Effective Pages\\ABC-1234-00-02c0";

        if (trait == 50) {
            rootPath = rootPath + "1.pdf";
            this.caminhoRelatorio = rootPath;
        }
        else if (trait == 55) {
            rootPath  = rootPath + "2.pdf";
            this.caminhoRelatorio = rootPath;
        }
        else if (trait == 60) {
            rootPath  = rootPath + "3.pdf";
            this.caminhoRelatorio = rootPath;
        }

        this.classeLEP = classeLEP;

        // Criando o objeto que vai ser o PDF
        documentoPDF = new Document();
        try {
            // Passando o objeto que criamos e o local aonde vai ser salvo o PDF
            PdfWriter.getInstance(documentoPDF, new FileOutputStream(caminhoRelatorio));
            // Abrindo o PDF para edicao
            documentoPDF.open();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void gerarCabecalhoPaginaDeRevisao() {
        // Quebrando a sessao
        this.adicionarQuebraDeSessao();

        // Texto que fica acima do titulo
        // Criando o paragrafo
        Paragraph textoAcimaTitulo = new Paragraph();
        // Adicionando o alinhamento
        textoAcimaTitulo.setAlignment(Element.ALIGN_RIGHT);
        // Setando o texto
        textoAcimaTitulo.add(new Chunk("List of Effective Pages", new Font(Font.HELVETICA, 14)));
        // Adicionando o conteudo ao PDF
        documentoPDF.add(textoAcimaTitulo);
        //Pulando linha
        this.pularLinha();
        this.pularLinha();

        // Titulo
        Paragraph textoTitulo = new Paragraph();
        // Adicionando o alinhamento
        textoTitulo.setAlignment(Element.ALIGN_CENTER);
        // Setando o texto
        textoTitulo.add(new Chunk("LIST OF EFFECTIVE PAGES", new Font(Font.HELVETICA, 24)));
        // Adicionando o conteudo ao PDF
        documentoPDF.add(textoTitulo);
        //Pulando linha
        this.pularLinha();
    }

    public void gerarCorpoPaginaDeRevisao() {
        // Criando o paragrafo
        Paragraph revisao1 = new Paragraph();
        // Adicionando o alinhamento
        revisao1.setAlignment(Element.ALIGN_CENTER);
        // Setando o texto
        revisao1.add(new Chunk("ORIGINAL...........0........... DEC 09, 2010", new Font(Font.HELVETICA, 14)));
        // Adicionando o conteudo ao PDF
        documentoPDF.add(revisao1);

        // Criando o paragrafo
        Paragraph revisao2 = new Paragraph();
        // Adicionando o alinhamento
        revisao2.setAlignment(Element.ALIGN_CENTER);
        // Setando o texto
        revisao2.add(new Chunk("REVISION...........1........... DEC 29, 2013", new Font(Font.HELVETICA, 14)));
        // Adicionando o conteudo ao PDF
        documentoPDF.add(revisao2);

        // Criando o paragrafo
        Paragraph revisao3 = new Paragraph();
        // Adicionando o alinhamento
        revisao3.setAlignment(Element.ALIGN_CENTER);
        // Setando o texto
        revisao3.add(new Chunk("REVISION...........2........... AUG 19, 2014", new Font(Font.HELVETICA, 14)));
        // Adicionando o conteudo ao PDF
        documentoPDF.add(revisao3);

        // Criando o paragrafo
        Paragraph revisao4 = new Paragraph();
        // Adicionando o alinhamento
        revisao4.setAlignment(Element.ALIGN_CENTER);
        // Setando o texto
        revisao4.add(new Chunk("REVISION...........3........... OCT 20, 2016", new Font(Font.HELVETICA, 14)));
        // Adicionando o conteudo ao PDF
        documentoPDF.add(revisao4);

        // Criando o paragrafo
        Paragraph revisao5 = new Paragraph();
        // Adicionando o alinhamento
        revisao5.setAlignment(Element.ALIGN_CENTER);
        // Setando o texto
        revisao5.add(new Chunk("REVISION...........4........... DEC 21, 2017", new Font(Font.HELVETICA, 14)));
        // Adicionando o conteudo ao PDF
        documentoPDF.add(revisao5);

        // Criando o paragrafo
        Paragraph revisao6 = new Paragraph();
        // Adicionando o alinhamento
        revisao6.setAlignment(Element.ALIGN_CENTER);
        // Setando o texto
        revisao6.add(new Chunk("REVISION...........5........... MAR 23, 2018", new Font(Font.HELVETICA, 14)));
        // Adicionando o conteudo ao PDF
        documentoPDF.add(revisao6);

        // Criando o paragrafo
        Paragraph revisao7 = new Paragraph();
        // Adicionando o alinhamento
        revisao7.setAlignment(Element.ALIGN_CENTER);
        // Setando o texto
        revisao7.add(new Chunk("REVISION...........6........... MAY 19, 2020", new Font(Font.HELVETICA, 14)));
        // Adicionando o conteudo ao PDF
        documentoPDF.add(revisao7);

        this.pularLinha();
        this.pularLinha();
        this.pularLinha();
        this.pularLinha();
        this.pularLinha();
        this.pularLinha();
        this.pularLinha();
        this.pularLinha();
        this.pularLinha();
        this.pularLinha();
        this.pularLinha();
        this.pularLinha();
        this.pularLinha();
        this.pularLinha();
        this.pularLinha();
        this.pularLinha();
        this.pularLinha();
        this.pularLinha();
        this.pularLinha();
        this.pularLinha();
        this.pularLinha();
        this.pularLinha();
        this.pularLinha();
        this.pularLinha();
        this.pularLinha();
        this.pularLinha();

        this.adicionarRodaPe();
    }

    public void gerarCabecalhoPaginaDosBlocos() {
        // Quebrando a sessao
        this.adicionarQuebraDeSessao();

        // Texto que fica acima do titulo
        // Criando o paragrafo
        Paragraph textoAcimaTitulo = new Paragraph();
        // Adicionando o alinhamento
        textoAcimaTitulo.setAlignment(Element.ALIGN_RIGHT);
        // Setando o texto
        textoAcimaTitulo.add(new Chunk("List of Effective Pages", new Font(Font.HELVETICA, 14)));
        // Adicionando o conteudo ao PDF
        documentoPDF.add(textoAcimaTitulo);

        // Quebrando a sessao
        this.adicionarQuebraDeSessao();

        //Pulando linha
        this.pularLinha();
        this.pularLinha();

    }

    public void gerarCorpoPaginaDosBlocos() {
        PdfPTable tableLEP = this.criarTabelaComCabecalho();
        this.adicionarProdutosATabela(tableLEP);
        this.documentoPDF.add(tableLEP);
    }

    public void imprimir() {
        // Se o documento não for nulo e não estiver aberto
        if(this.documentoPDF != null && this.documentoPDF.isOpen()){
            // Fechando o documento
            documentoPDF.close();
        }
    }

    private void adicionarQuebraDeSessao() {
        Paragraph paragrafoSessao = new Paragraph("_____________________________________________________________________________");
        paragrafoSessao.setAlignment(Element.ALIGN_CENTER);
        this.documentoPDF.add(paragrafoSessao);
    }

    private void pularLinha() {
        this.documentoPDF.add(new Paragraph(" "));
    }

    private void adicionarRodaPe() {
        Paragraph pRodape = new Paragraph();
        pRodape.setAlignment(Element.ALIGN_CENTER);
        pRodape.add(new Chunk("0-LEP", new Font(Font.HELVETICA, 24)));
        this.documentoPDF.add(pRodape);
        this.adicionarQuebraDeSessao();

        // Criando o paragrafo
        Paragraph rodape = new Paragraph();
        // Adicionando o alinhamento
        rodape.setAlignment(Element.ALIGN_CENTER);
        // Setando o texto
        rodape.add(new Chunk("REVISION 06                                        code 01                                        Page 1", new Font(Font.HELVETICA, 14)));
        // Adicionando o conteudo ao PDF
        documentoPDF.add(rodape);
    }

    private PdfPTable criarTabelaComCabecalho() {
        // tabela com 4 colunas
        PdfPTable tableLEP = new PdfPTable(4);
        tableLEP.setWidthPercentage(98);
        tableLEP.setWidths(new float[] { 1f, 1f, 1f, 1f });

        PdfPCell celulaTitulo = new PdfPCell(new Phrase("Block"));
        celulaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
        celulaTitulo.setBackgroundColor(Color.LIGHT_GRAY);
        tableLEP.addCell(celulaTitulo);

        celulaTitulo = new PdfPCell(new Phrase("Code"));
        celulaTitulo.setBackgroundColor(Color.LIGHT_GRAY);
        celulaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableLEP.addCell(celulaTitulo);

        celulaTitulo = new PdfPCell(new Phrase("Page"));
        celulaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
        celulaTitulo.setBackgroundColor(Color.LIGHT_GRAY);
        tableLEP.addCell(celulaTitulo);

        celulaTitulo = new PdfPCell(new Phrase("Change"));
        celulaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
        celulaTitulo.setBackgroundColor(Color.LIGHT_GRAY);
        tableLEP.addCell(celulaTitulo);

        return tableLEP;
    }

    private void adicionarProdutosATabela(PdfPTable tableLEP) {

        int contador = 1;
        for (ItensLEP itensLEP : this.classeLEP.getBlocosSelecionados()) {

            PdfPCell celulaBlock = new PdfPCell(new Phrase(itensLEP.getBlock()));
            celulaBlock.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell celulaCode = new PdfPCell(new Phrase(String.valueOf(itensLEP.getCode())));
            celulaCode.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell celulaPage = new PdfPCell(new Phrase(new Phrase(String.valueOf(itensLEP.getPage()))));
            celulaPage.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell celulaChange = new PdfPCell(new Phrase(new Phrase(String.valueOf(itensLEP.getChange()))));
            celulaChange.setHorizontalAlignment(Element.ALIGN_CENTER);

            if (contador % 2 == 0) {
                celulaBlock.setBackgroundColor(Color.LIGHT_GRAY);
                celulaCode.setBackgroundColor(Color.LIGHT_GRAY);
                celulaPage.setBackgroundColor(Color.LIGHT_GRAY);
                celulaChange.setBackgroundColor(Color.LIGHT_GRAY);
            }

            tableLEP.addCell(celulaBlock);
            tableLEP.addCell(celulaCode);
            tableLEP.addCell(celulaPage);
            tableLEP.addCell(celulaChange);

            contador++;
        }
    }

}