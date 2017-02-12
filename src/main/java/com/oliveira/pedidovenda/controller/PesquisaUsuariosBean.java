package com.oliveira.pedidovenda.controller;

import com.oliveira.pedidovenda.model.Usuario;
import com.oliveira.pedidovenda.repository.Usuarios;
import com.oliveira.pedidovenda.repository.filter.UsuarioFilter;
import com.oliveira.pedidovenda.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Adriano
 */
@Named
@ViewScoped
public class PesquisaUsuariosBean implements Serializable {

    private static final long serialVersionUID = 0L;

    @Inject
    private Usuarios usuarios;

    private final UsuarioFilter filtro;
    private List<Usuario> usuariosFiltrados;

    private Usuario usuarioSelecionado;

    public PesquisaUsuariosBean() {
        filtro = new UsuarioFilter();
        usuariosFiltrados = new ArrayList<>();
       
    }

    public void excluir() {
        usuarios.remover(usuarioSelecionado);
        usuariosFiltrados.remove(usuarioSelecionado);

        FacesUtil.addInfoMessage("Usuario" + usuarioSelecionado.getId() + "excluido com sucesso");

    }

    public void pesquisar() {
        usuariosFiltrados = usuarios.filtrados(filtro);
    }

    public UsuarioFilter getFiltro() {
        return filtro;
    }

    public List<Usuario> getUsuariosFiltrados() {
        return usuariosFiltrados;
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

}
