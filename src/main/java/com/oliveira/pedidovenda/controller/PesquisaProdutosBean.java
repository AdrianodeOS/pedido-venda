package com.oliveira.pedidovenda.controller;

import com.oliveira.pedidovenda.model.Produto;
import com.oliveira.pedidovenda.repository.Produtos;
import com.oliveira.pedidovenda.repository.filter.ProdutoFilter;
import com.oliveira.pedidovenda.util.jsf.FacesUtil;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class PesquisaProdutosBean implements Serializable {

    private static final long serialVersionUID = 0L;

    @Inject
    private Produtos produtos;

    private final ProdutoFilter filtro;
    private List<Produto> produtosFiltrados;

    private Produto produtoSelecionado;

    public PesquisaProdutosBean() {
        filtro = new ProdutoFilter();
    }

    public void excluir() {
        produtos.remover(produtoSelecionado);
        produtosFiltrados.remove(produtoSelecionado);

        FacesUtil.addInfoMessage("Produto" + produtoSelecionado.getSku() + "excluido com sucesso");

    }

    public void pesquisar() {
        produtosFiltrados = produtos.filtrados(filtro);
    }

    public ProdutoFilter getFiltro() {
        return filtro;
    }

    public List<Produto> getProdutosFiltrados() {
        return produtosFiltrados;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }


    
}
