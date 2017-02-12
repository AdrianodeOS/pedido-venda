package com.oliveira.pedidovenda.converter;

import com.oliveira.pedidovenda.model.Usuario;
import com.oliveira.pedidovenda.repository.Usuarios;
//import com.oliveira.pedidovenda.repository.filter.UsuarioFilter;
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
@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

    // @Inject
    private Usuarios usuarios;

    public UsuarioConverter() {
        this.usuarios = (Usuarios) CDIServiceLocator.getBean(Usuarios.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Usuario retorno = null;

        if (StringUtils.isNotEmpty(value)) {
            //retorno = this.usuarios.porId(new Long(value));
            Long id = new Long(value);
            retorno = usuarios.porId(id);
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            //return ((Usuario) value).getId().toString();
            Usuario usuario = (Usuario) value;
            return usuario.getId() == null ? null : usuario.getId().toString();
            
        }

        return "";
    }

}
