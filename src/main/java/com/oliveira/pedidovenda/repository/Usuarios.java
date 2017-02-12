package com.oliveira.pedidovenda.repository;

import com.oliveira.pedidovenda.model.Usuario;
import com.oliveira.pedidovenda.repository.filter.UsuarioFilter;
import com.oliveira.pedidovenda.service.NegocioException;
import com.oliveira.pedidovenda.util.jpa.Transactional;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Adriano
 */
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 0L;

    @Inject
    private EntityManager manager;

    public Usuario guardar(Usuario usuario) {
        return manager.merge(usuario);
    }

    @Transactional
    public void remover(Usuario usuario) {
        try {
            usuario = porIdE(usuario.getId());
            manager.remove(usuario);
            manager.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("Usuario " + usuario.getNome() + " não pode ser excluído.");
        }
    }

    public List<Usuario> vendedores() {
        // TODO filtrar apenas vendedores (por um grupo específico)
        return this.manager.createQuery("from Usuario", Usuario.class)
                .getResultList();
    }

    /*public Usuario porId(Long id) {
     return manager.find(Usuario.class, id);
     }*/
    public Usuario porId(Long id) {
        try {
            return manager.createQuery("from Usuario where id = :id", Usuario.class).setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Usuario porNomeOuEmail(String nome, String email) {
        try {
            return manager.createQuery("from Usuario where upper(nome) = :nome and email = :email", Usuario.class)
                    .setParameter("nome", nome.toUpperCase())
                    .setParameter("email", email.toUpperCase())
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Usuario porEmail(String email) {
        Usuario usuario = null;

        try {
            usuario = this.manager.createQuery("from Usuario where lower(email) = :email", Usuario.class)
                    .setParameter("email", email.toLowerCase()).getSingleResult();
        } catch (NoResultException e) {
            // nenhum usuário encontrado com o e-mail informado
        }

        return usuario;
    }

    //@SuppressWarnings("unchecked")
    public List<Usuario> filtrados(UsuarioFilter filtro) {
        Session session = manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Usuario.class);

        if (StringUtils.isNotBlank(filtro.getNome())) {
            criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
        }

        return criteria.addOrder(Order.asc("nome")).list();
    }

    public Usuario porIdE(Long id) {
        return this.manager.find(Usuario.class, id);
    }
}
