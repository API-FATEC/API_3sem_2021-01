package com.fatec.mom.domain.lep;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.GroupLayout.Alignment;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class RelatorioPDF implements Relatorio{
    private ClasseLEP classeLEP;
    private Document documentoPDF;
    private String caminhoRelatorio = "D:/LEP.pdf";

    public RelatorioPDF(ClasseLEP classeLEP) {

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

    public void gerarCabecalho() {
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
        // Quebrando sessao
        this.adicionarQuebraDeSessao();
        //Pulando linha
        this.pularLinha();
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
}