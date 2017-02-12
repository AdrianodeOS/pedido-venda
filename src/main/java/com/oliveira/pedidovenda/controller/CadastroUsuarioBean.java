/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oliveira.pedidovenda.controller;

import com.oliveira.pedidovenda.model.Grupo;
import com.oliveira.pedidovenda.model.Usuario;
import com.oliveira.pedidovenda.repository.Grupos;
import com.oliveira.pedidovenda.service.CadastroUsuarioService;
import com.oliveira.pedidovenda.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Adriano de Oliveira
 */
@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

    private static final long serialVersionUID = 0L;

    @Produces
    @UsuarioEdicao
    private Usuario usuario;
    private Grupo grupoSelecionado;
    private List<Grupo> grupo = new ArrayList<>();

    @Inject
    private CadastroUsuarioService cadastroUsuarioService;

    @Inject
    private Grupos grupos;

    public CadastroUsuarioBean() {
        limpar();
    }
    

    @PostConstruct
    public void inicializar() {
      
        if (FacesUtil.isNotPostback()) {
            grupo = grupos.grupos();
            grupoSelecionado = new Grupo();
        }

    }

    public void limpar() {
        usuario = new Usuario();
        grupo = new ArrayList<>();
       
    }

    public void salvar() {

        adicionarGrupo();
        this.usuario = cadastroUsuarioService.salvar(this.usuario);

        limpar();
        FacesUtil.addInfoMessage("Usuario cadastro com sucesso!");
    }

    public void adicionarGrupo() {
        this.usuario.getGrupos().add(grupoSelecionado);
    }

    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;

        if (this.usuario != null) {
            usuario.getGrupos();

        }
    }

    public List<Grupo> getGrupo() {
        return grupo;
    }

    public void setGrupo(List<Grupo> grupo) {
        this.grupo = grupo;

        if (this.grupo != null) {
            usuario.getGrupos();

        }
    }

    public Grupo getGrupoSelecionado() {
        return grupoSelecionado;
    }

    public void setGrupoSelecionado(Grupo grupoSelecionado) {
        this.grupoSelecionado = grupoSelecionado;
    }

    public boolean isEditando() {
        return this.usuario != null && this.usuario.getId() != null;
    }
}
