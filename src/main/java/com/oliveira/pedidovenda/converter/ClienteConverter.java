/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oliveira.pedidovenda.converter;

import com.oliveira.pedidovenda.model.Cliente;
import com.oliveira.pedidovenda.repository.Clientes;
import com.oliveira.pedidovenda.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
//import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Adriano
 */
@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements Converter {

   // @Inject
    private Clientes clientes;

    public ClienteConverter() {
        clientes = CDIServiceLocator.getBean(Clientes.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Cliente retorno = null;

        if (StringUtils.isNotEmpty(value)) {
            //retorno = this.clientes.porId(new Long(value));
            Long id = new Long(value);
            retorno = clientes.porId(id);
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            //return ((Cliente) value).getId().toString();
            Cliente cliente = (Cliente) value;
            return cliente.getId() == null ? null : cliente.getId().toString();
        }

        return "";
    }

}
