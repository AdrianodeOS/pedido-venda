/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oliveira.pedidovenda.controller;

/**
 *
 * @author Adriano
 */
import com.oliveira.pedidovenda.util.jsf.FacesUtil;
import com.oliveira.pedidovenda.util.report.ExecutorRelatorio;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.hibernate.Session;

@Named
@RequestScoped
public class RelatorioPedidoClienteBean implements Serializable {

    private static final long serialVersionUID = 0L;

    private Long numeroPedido;


    @Inject
    private FacesContext facesContext;

    @Inject
    private HttpServletResponse response;

    @Inject
    private EntityManager manager;

    public void emitir() {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("numeropedido", this.numeroPedido);

        ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/rel_cliente_pedido.jasper",
                this.response, parametros, "Pedidos emitidos.pdf");

        Session session = manager.unwrap(Session.class);
        session.doWork(executor);

        if (executor.isRelatorioGerado()) {
            facesContext.responseComplete();
        } else {
            FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
        }
    }

    @NotNull
    public Long getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(Long numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

}
