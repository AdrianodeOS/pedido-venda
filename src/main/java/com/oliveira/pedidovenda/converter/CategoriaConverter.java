/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oliveira.pedidovenda.converter;

import com.oliveira.pedidovenda.model.Categoria;
import com.oliveira.pedidovenda.repository.Categorias;
import com.oliveira.pedidovenda.util.cdi.CDIServiceLocator;
import java.util.Objects;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Adriano de Oliveira
 */
@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

    // @Inject
    private final Categorias categorias;

    public CategoriaConverter() {
        categorias = CDIServiceLocator.getBean(Categorias.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Categoria retorno = null;

        if (value != null) {
            Long id = new Long(value);
            retorno = categorias.porId(id);
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Categoria categoria = (Categoria) value;
            return categoria.getId() == null ? "" : categoria.getId().toString();
// return ((Categoria) value).getId().toString();
        }
        return "";
    }

}
