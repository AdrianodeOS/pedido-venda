package com.oliveira.pedidovenda.service;

import com.oliveira.pedidovenda.model.Usuario;
import com.oliveira.pedidovenda.repository.Usuarios;
import com.oliveira.pedidovenda.util.jpa.Transactional;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author Adriano
 */
public class CadastroUsuarioService implements Serializable {

    private static final long serialVersionUID = 0L;

    @Inject
    private Usuarios usuarios;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        Usuario usuarioExistente = usuarios.porId(usuario.getId());

        if (usuarioExistente != null && !usuarioExistente.equals(usuario)) {
            throw new NegocioException("Já existe um usuario informado.");
        }

        return usuarios.guardar(usuario);
    }

}
