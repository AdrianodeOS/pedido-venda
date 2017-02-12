package com.oliveira.pedidovenda.model;

public enum TipoPessoa {

    FISICA("Fisíca"),
    JURIDICA("Jurídica");

    private String descricao;

    TipoPessoa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
