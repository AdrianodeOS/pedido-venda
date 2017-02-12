package com.oliveira.pedidovenda.service;

import com.oliveira.pedidovenda.model.Pedido;
import com.oliveira.pedidovenda.model.StatusPedido;
import com.oliveira.pedidovenda.repository.Pedidos;
import com.oliveira.pedidovenda.util.jpa.Transactional;
import java.io.Serializable;
import java.util.Date;
import javax.inject.Inject;

/**
 *
 * @author Adriano
 */
public class CadastroPedidoService implements Serializable {

    private static final long serialVersionUID = 0L;

    @Inject
    private Pedidos pedidos;

    @Transactional
	public Pedido salvar(Pedido pedido) {
		if (pedido.isNovo()) {
			pedido.setDataCriacao(new Date());
			pedido.setStatus(StatusPedido.ORCAMENTO);
		}
		
		pedido.recalcularValorTotal();
		
		if (pedido.isNaoAlteravel()) {
			throw new NegocioException("Pedido não pode ser alterado no status "
					+ pedido.getStatus().getDescricao() + ".");
		}
		
		if (pedido.getItens().isEmpty()) {
			throw new NegocioException("O pedido deve possuir pelo menos um item.");
		}
		
		if (pedido.isValorTotalNegativo()) {
			throw new NegocioException("Valor total do pedido não pode ser negativo.");
		}
		
		pedido = this.pedidos.guardar(pedido);
		return pedido;
	}
}
