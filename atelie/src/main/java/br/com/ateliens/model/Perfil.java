package br.com.ateliens.model;

 
/**
 *
 * @author Yvonei
 */

public enum Perfil{
    ADMINISTRADOR("Administrador"),
    FUNCIONARIO("Funcionario");
    
     private String descricao;

    Perfil(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
