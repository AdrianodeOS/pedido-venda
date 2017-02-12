/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oliveira.pedidovenda.controller;

import com.oliveira.pedidovenda.model.Cliente;
import com.oliveira.pedidovenda.model.TipoPessoa;
import com.oliveira.pedidovenda.repository.Clientes;
import com.oliveira.pedidovenda.repository.filter.ClienteFilter;
import com.oliveira.pedidovenda.util.jsf.FacesUtil;
import java.io.Serializable;
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
public class PesquisaClientesBean implements Serializable {

    private static final long serialVersionUID = 0L;

    @Inject
    private Clientes clientes;

    private final ClienteFilter filtro;
    private List<Cliente> clientesFiltrados;

    private Cliente clienteSelecionado;

    public PesquisaClientesBean() {
        filtro = new ClienteFilter();
    }

    public void excluir() {
        clientes.remover(clienteSelecionado);
        clientesFiltrados.remove(clienteSelecionado);

        FacesUtil.addInfoMessage("Usuario" + clienteSelecionado.getId() + "excluido com sucesso");

    }

    public void pesquisar() {
        clientesFiltrados = clientes.filtrados(filtro);
    }

    public ClienteFilter getFiltro() {
        return filtro;
    }

    public List<Cliente> getClientesFiltrados() {
        return clientesFiltrados;
    }

    public TipoPessoa[] getTipoPessoa() {
        return TipoPessoa.values();
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }

}
