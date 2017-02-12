package com.oliveira.pedidovenda.util.mail;

import com.outjected.email.api.MailMessage;
import com.outjected.email.api.SessionConfig;
import com.outjected.email.impl.MailMessageImpl;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Adriano
 */
@RequestScoped
public class Mailer implements Serializable {

    private static final long serialVersionUID = 0L;

    @Inject
    private SessionConfig config;

    public MailMessage novaMensagem() {
        return new MailMessageImpl(this.config).from(this.config.getUsername());
        //return new MailMessageImpl(this.config);
    }

}
