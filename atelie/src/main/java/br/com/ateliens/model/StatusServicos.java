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
public enum StatusServicos {
    PAGO("Pago"),
    PENDENTE("Pendente"),
    DOADO("Doado"),
    ABATER("Abater");

    private String descricao;

    StatusServicos(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
