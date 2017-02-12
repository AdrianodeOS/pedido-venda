package com.oliveira.pedidovenda.converter;

import com.oliveira.pedidovenda.model.Pedido;
import com.oliveira.pedidovenda.repository.Pedidos;
import com.oliveira.pedidovenda.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Adriano
 */
@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter {

    @Inject
    private Pedidos pedidos;

    public PedidoConverter() {
        pedidos = CDIServiceLocator.getBean(Pedidos.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Pedido retorno = null;

        if (StringUtils.isNotEmpty(value)) {
            Long id = new Long(value);
            retorno = pedidos.porId(id);
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Pedido pedido = (Pedido) value;
            return pedido.getId() == null ? null : pedido.getId().toString();
        }

        return "";
    }

}
