package com.oliveira.pedidovenda.repository;

import com.oliveira.pedidovenda.model.Produto;
import com.oliveira.pedidovenda.repository.filter.ProdutoFilter;
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
public class Produtos implements Serializable {

    private static final long serialVersionUID = 0L;

    @Inject
    private EntityManager manager;

    public Produto guardar(Produto produto) {
        manager.merge(produto);
        //manager.flush();
        System.out.println("Produto armazenado " + produto.getId());
        return produto;
    }

    @Transactional
    public void remover(Produto produto) {
        try {
            produto = porId(produto.getId());
            manager.remove(produto);
            manager.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("Produto não pode ser excluido");
        }
    }

    public Produto porSku(String sku) {
        try {
            return manager.createQuery("from Produto where upper(sku) = :sku", Produto.class)
                    .setParameter("sku", sku.toUpperCase()).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    // @SuppressWarnings("uncheked") quando estiver reclamando codigo em "amarelo" retorna lista tipada order
    public List<Produto> filtrados(ProdutoFilter filtro) {
        Session session = manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Produto.class);

        if (StringUtils.isNotBlank(filtro.getSku())) {
            criteria.add(Restrictions.eq("sku", filtro.getSku()));
        }
        if (StringUtils.isNotBlank(filtro.getNome())) {
            criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));//where queoque apareça antes ou depois "%joao%" //wherre nome like'%joao%'
        }
        return criteria.addOrder(Order.asc("nome")).list();
    }

    public Produto porId(Long id) {
        return manager.find(Produto.class, id);
    }

    public List<Produto> porNome(String nome) {
        return this.manager.createQuery("from Produto where upper(nome) like :nome", Produto.class)
                .setParameter("nome", nome.toUpperCase() + "%").getResultList();
    }

}
