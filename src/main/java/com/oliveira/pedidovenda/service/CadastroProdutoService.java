package com.oliveira.pedidovenda.service;


import com.oliveira.pedidovenda.model.Produto;
import com.oliveira.pedidovenda.repository.Produtos;
import com.oliveira.pedidovenda.util.jpa.Transactional;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author Adriano
 */
public class CadastroProdutoService implements Serializable {



    @Inject
    private Produtos produtos;

    @Transactional
    public Produto salvar(Produto produto) {
   
        Produto produtoExistente = produtos.porSku(produto.getSku());
        
        if(produtoExistente != null && !produtoExistente.equals(produto)){
            throw new NegocioException("Já existe um produto com sku informado.");
        }
        return produtos.guardar(produto);
    }
}
