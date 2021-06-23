package com.fatec.mom.domain.lep;

public interface Relatorio {
    public void gerarCabecalhoPaginaDeRevisao();
    public void gerarCorpoPaginaDeRevisao();
    public void gerarCabecalhoPaginaDosBlocos();
    public void gerarCorpoPaginaDosBlocos();
    //public void gerarRodape();
    public void imprimir();
}