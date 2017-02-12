package com.oliveira.pedidovenda.controller;

import com.oliveira.pedidovenda.model.Categoria;
import com.oliveira.pedidovenda.model.Produto;
import com.oliveira.pedidovenda.repository.Categorias;
import com.oliveira.pedidovenda.service.CadastroProdutoService;
import com.oliveira.pedidovenda.util.jsf.FacesUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Adriano de Oliveira
 */
@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

    public static final long serialVersionUID = 0L;

    @Inject
    private Categorias categorias;

    @Inject
    private CadastroProdutoService cadastroProdutoService;

    private List<Categoria> categoriasRaizes;
    private List<Categoria> subcategorias;

    private Produto produto;
    private Categoria categoriaPai;

    public CadastroProdutoBean() {
        limpar();
    }

    @PostConstruct
    public void inicializar() {
        this.produto = new Produto();
        if (FacesUtil.isNotPostback()) {
            categoriasRaizes = categorias.raizes();
            if (this.categoriaPai != null && this.produto != null) {
                this.carregarSubCategorias();
            }
        }

    }

    public void carregarSubCategorias() {

        subcategorias = categorias.subcategoriasDe(categoriaPai);
    }

    private void limpar() {
        this.produto = new Produto();
        categoriaPai = null;
        subcategorias = new ArrayList<>();
    }

    public void salvar() {
        this.produto = cadastroProdutoService.salvar(this.produto);

        limpar();

        FacesUtil.addInfoMessage("Produto Salvo com sucesso!");
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;

        if (this.produto != null) {
            this.categoriaPai = this.produto.getCategoria().getCategoriaPai();

        }
    }

    public List<Categoria> getCategoriasRaizes() {
        return categoriasRaizes;
    }

    @NotNull
    public Categoria getCategoriaPai() {
        return categoriaPai;
    }

    public void setCategoriaPai(Categoria categoriaPai) {
        this.categoriaPai = categoriaPai;
    }

    public List<Categoria> getSubcategorias() {
        return subcategorias;
    }

    public void setSubcategorias(List<Categoria> subcategorias) {
        this.subcategorias = subcategorias;
    }

    public boolean isEditando() {
        return this.produto != null && this.produto.getId() != null;
    }

}
