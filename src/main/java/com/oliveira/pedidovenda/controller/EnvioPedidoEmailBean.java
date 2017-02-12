package com.oliveira.pedidovenda.controller;

import com.oliveira.pedidovenda.model.Pedido;
import com.oliveira.pedidovenda.util.jsf.FacesUtil;
import com.oliveira.pedidovenda.util.mail.Mailer;

import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.inject.Named;
import org.apache.velocity.tools.generic.NumberTool;

/**
 *
 * @author Adriano
 */
@Named
@RequestScoped
public class EnvioPedidoEmailBean implements Serializable {

    private static final long serialVersionUID = 0L;

    @Inject
    private Mailer mailer;

    @Inject
    @PedidoEdicao
    private Pedido pedido;

    public void enviarPedido() {
        MailMessage message = mailer.novaMensagem();

        message.to(this.pedido.getCliente().getEmail())
                .subject("pedido" + this.pedido.getId())
                .bodyHtml(new VelocityTemplate(getClass().getResourceAsStream("/emails/pedido.template")))
                .put("pedido", this.pedido)
                .put("numberTool", new NumberTool())
                .put("locale", new Locale("pt", "BR"))
                .send();

        FacesUtil.addInfoMessage("Pedido enviado por e-mail com sucesso!");
    }

}
