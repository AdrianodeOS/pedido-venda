/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oliveira.pedidovenda.repository;

import com.oliveira.pedidovenda.model.Cliente;
import com.oliveira.pedidovenda.repository.filter.ClienteFilter;
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
public class Clientes implements Serializable {

    private static final long serialVersionUID = 0L;

    @Inject
    private EntityManager manager;

    public Cliente guardar(Cliente cliente) {
        return manager.merge(cliente);
    }

    @Transactional
    public void remover(Cliente cliente) {
        try {
            cliente = porIdE(cliente.getId());
            manager.remove(cliente);
            manager.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("Cliente " + cliente.getNome() + " não pode ser excluído.");
        }
    }

    public List<Cliente> porNome(String nome) {
        return this.manager.createQuery("from Cliente "
                + "where upper(nome) like :nome", Cliente.class)
                .setParameter("nome", nome.toUpperCase() + "%")
                .getResultList();
    }

    public Cliente porDocumento(String documento) {
        try {
            return manager.createQuery("from Cliente where upper(documentoReceitaFederal) = :documento", Cliente.class)
                    .setParameter("documento", documento.toUpperCase()).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /*public Usuario porId(Long id) {
     return manager.find(Usuario.class, id);
     }*/
    public Cliente porId(Long id) {
        try {
            return manager.createQuery("from Cliente where id = :id", Cliente.class
            ).setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Cliente porNomeOuEmail(String nome, String email) {
        try {
            return manager.createQuery("from Cliente where upper(nome) = :nome and email = :email", Cliente.class
            )
                    .setParameter("nome", nome.toUpperCase())
                    .setParameter("email", email.toUpperCase())
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Cliente porEmail(String email) {
        try {
            return manager.createQuery("from Cliente where upper(email) = :email", Cliente.class
            )
                    .setParameter("email", email.toUpperCase())
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    //@SuppressWarnings("unchecked")
    public List<Cliente> filtrados(ClienteFilter filtro) {
        Session session = manager.unwrap(Session.class
        );
        Criteria criteria = session.createCriteria(Cliente.class
        );

        if (StringUtils.isNotBlank(filtro.getNome())) {
            criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
        }

        return criteria.addOrder(Order.asc("nome")).list();
    }

    public Cliente porIdE(Long id) {
        return manager.find(Cliente.class, id);
    }
}
