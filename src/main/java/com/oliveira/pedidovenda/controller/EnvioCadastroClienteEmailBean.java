package com.oliveira.pedidovenda.controller;

import com.oliveira.pedidovenda.model.Cliente;
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
public class EnvioCadastroClienteEmailBean implements Serializable {

    private static final long serialVersionUID = 0L;

    @Inject
    private Mailer mailer;

    @Inject
    @ClienteEdicao
    private Cliente cliente;

    public void enviarPedido() {
        MailMessage message = mailer.novaMensagem();

        message.to(this.cliente.getEmail())
                .subject("Cadastro Cliente" + this.cliente.getId())
                .bodyHtml(new VelocityTemplate(getClass().getResourceAsStream("/emails/cliente.template")))
                .put("cliente", this.cliente)
                .put("locale", new Locale("pt", "BR"))
                .send();

        FacesUtil.addInfoMessage("Cadastro CLiente enviado por e-mail com sucesso!");
    }

}
