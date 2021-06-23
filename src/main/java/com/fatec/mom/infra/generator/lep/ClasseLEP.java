package com.fatec.mom.infra.generator.lep;

import lombok.Data;

import java.util.List;

@Data
public class ClasseLEP {
    private List<ItensLEP> blocosSelecionados;

    public ClasseLEP(List<ItensLEP> blocos){
        this.blocosSelecionados = blocos;
    }

    public void addItensLEP(ItensLEP itensLEP){
        this.blocosSelecionados.add(itensLEP);
    }

    public List<ItensLEP> getItensLEP() {
        return this.blocosSelecionados;
    }
}