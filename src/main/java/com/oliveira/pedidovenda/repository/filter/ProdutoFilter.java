package com.oliveira.pedidovenda.repository.filter;

import com.oliveira.pedidovenda.validation.SKU;
import java.io.Serializable;

/**
 *
 * @author Adriano
 */
public class ProdutoFilter implements Serializable {

    private static final long serialVersionUID = 0L;
    
    private String sku;
    private String nome;
 
    @SKU
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.toUpperCase();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
}
