package com.oliveira.pedidovenda.service;

import com.oliveira.pedidovenda.model.Pedido;
import com.oliveira.pedidovenda.model.StatusPedido;
import com.oliveira.pedidovenda.repository.Pedidos;
import com.oliveira.pedidovenda.util.jpa.Transactional;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author Adriano
 */
public class EmissaoPedidoService implements Serializable {

    private static final long serialVersionUID = 0L;

    @Inject
    private CadastroPedidoService cadastroPedidoService;

    @Inject
    private EstoqueService estoqueService;

    @Inject
    private Pedidos pedidos;

    @Transactional
    public Pedido emitir(Pedido pedido) {
        pedido = this.cadastroPedidoService.salvar(pedido);

        if (pedido.isNaoEmissivel()) {
            throw new NegocioException("Pedido não pode ser emitido com status "
                    + pedido.getStatus().getDescricao() + ".");
        }

        this.estoqueService.baixarItensEstoque(pedido);

        pedido.setStatus(StatusPedido.EMITIDO);

        pedido = this.pedidos.guardar(pedido);

        return pedido;
    }

}
