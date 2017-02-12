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
public class CancelamentoPedidoService implements Serializable {

	private static final long serialVersionUID = 0L;

	@Inject
	private Pedidos pedidos;
	
	@Inject
	private EstoqueService estoqueService;
	
	@Transactional
	public Pedido cancelar(Pedido pedido) {
		pedido = this.pedidos.porId(pedido.getId());
		
		if (pedido.isNaoCancelavel()) {
			throw new NegocioException("Pedido não pode ser cancelado no status "
					+ pedido.getStatus().getDescricao() + ".");
		}
		
		if (pedido.isEmitido()) {
			this.estoqueService.retornarItensEstoque(pedido);
		}
		
		pedido.setStatus(StatusPedido.CANCELADO);
		
		pedido = this.pedidos.guardar(pedido);
		
		return pedido;
	}

}
