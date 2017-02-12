package com.oliveira.pedidovenda.repository.filter;

import java.io.Serializable;

/**
 *
 * @author Adriano
 */
public class GrupoFilter implements Serializable {

    private static final long serialVersionUID = 0L;
    
    private String nome;
    private String descricao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    

}
