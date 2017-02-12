package com.oliveira.pedidovenda.controller;

import com.oliveira.pedidovenda.model.Pedido;
import com.oliveira.pedidovenda.model.StatusPedido;
import com.oliveira.pedidovenda.repository.Pedidos;
import com.oliveira.pedidovenda.repository.filter.PedidoFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class PesquisaPedidosBean implements Serializable {

    private static final long serialVersionUID = 0L;

    @Inject
    private Pedidos pedidos;

    private PedidoFilter filtro;
    private List<Pedido> pedidosFiltrados;

    public PesquisaPedidosBean() {
        filtro = new PedidoFilter();
        pedidosFiltrados = new ArrayList<>();
    }

    public void pesquisar() {
        pedidosFiltrados = pedidos.filtrados(filtro);
    }

    public StatusPedido[] getStatuses() {
        return StatusPedido.values();
    }

    public List<Pedido> getPedidosFiltrados() {
        return pedidosFiltrados;
    }

    public PedidoFilter getFiltro() {
        return filtro;
    }

}
