/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oliveira.pedidovenda.converter;

import com.oliveira.pedidovenda.model.Grupo;
import com.oliveira.pedidovenda.repository.Grupos;
import com.oliveira.pedidovenda.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Adriano
 */
@FacesConverter(forClass = Grupo.class)
public class GrupoConverter implements Converter {

   // @Inject
    private Grupos grupos;

    public GrupoConverter() {
        grupos = CDIServiceLocator.getBean(Grupos.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Grupo retorno = null;

        if (StringUtils.isNotEmpty(value)) {
            Long id = new Long(value);
            retorno = grupos.porId(id);
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Grupo grupo = (Grupo) value;
			return grupo.getId() == null ? null : grupo.getId().toString();
        }
        return "";
    }

}
