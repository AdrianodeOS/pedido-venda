package com.oliveira.pedidovenda.service;

import com.oliveira.pedidovenda.model.ItemPedido;
import com.oliveira.pedidovenda.model.Pedido;
import com.oliveira.pedidovenda.repository.Pedidos;
import com.oliveira.pedidovenda.util.jpa.Transactional;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author Adriano
 */
public class EstoqueService implements Serializable {

    private static final long serialVersionUID = 0L;

    @Inject
    private Pedidos pedidos;

    @Transactional
    public void baixarItensEstoque(Pedido pedido) {
        pedido = this.pedidos.porId(pedido.getId());

        for (ItemPedido item : pedido.getItens()) {
            item.getProduto().baixarEstoque(item.getQuantidade());
        }
    }

    public void retornarItensEstoque(Pedido pedido) {
        pedido = this.pedidos.porId(pedido.getId());

        for (ItemPedido item : pedido.getItens()) {
            item.getProduto().adicionarEstoque(item.getQuantidade());
        }
    }

}
