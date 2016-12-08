/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ateliens.model;

/**
 *
 * @author Yvonei
 */
public enum Tipo {
    
    CONFECCAO("Confecções sob medida"),
    SERVICO("Serviços em geral");

    private String descricao;

    Tipo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
