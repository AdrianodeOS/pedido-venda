package com.oliveira.pedidovenda.controller;

import com.oliveira.pedidovenda.model.Pedido;
import com.oliveira.pedidovenda.service.EmissaoPedidoService;
import com.oliveira.pedidovenda.util.jsf.FacesUtil;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.event.Event;

/**
 *
 * @author Adriano
 */
@Named
@RequestScoped
public class EmissaoPedidoBean implements Serializable {

    private static final long serialVersionUID = 0L;

    @Inject
    private EmissaoPedidoService emissaoPedidoService;

    @Inject
    @PedidoEdicao
    private Pedido pedido;


    @Inject
    private Event<PedidoAlteradoEvent> pedidoAlteradoEvent;

    public void emitirPedido() {
        this.pedido.removerItemVazio();

        try {
            this.pedido = this.emissaoPedidoService.emitir(this.pedido);
            this.pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(this.pedido));

            FacesUtil.addInfoMessage("Pedido emitido com sucesso!");
        } finally {
            this.pedido.adicionarItemVazio();
        }
    }
}
