package br.com.ateliens.model;

public enum StatusFuncionario {

    ATIVO("Ativo"),
    INATIVO("Inativo"),
    FERIAS("Férias");

    private String descricao;

    StatusFuncionario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
