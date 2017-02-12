package com.oliveira.pedidovenda.repository;

import com.oliveira.pedidovenda.model.Endereco;
import com.oliveira.pedidovenda.repository.filter.EnderecoFilter;
import com.oliveira.pedidovenda.service.NegocioException;
import com.oliveira.pedidovenda.util.jpa.Transactional;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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
public class Enderecos implements Serializable {

    private static final long serialVersionUID = 0L;

    @Inject
    private EntityManager manager;

    public Endereco porId(Long id) {
        return manager.find(Endereco.class, id);
    }

    public Endereco guardar(Endereco endereco) {
        return manager.merge(endereco);
    }

    @Transactional
    public void remover(Endereco endereco) {
        try {
            endereco = porId(endereco.getId());
            manager.remove(endereco);
            manager.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("Produto não pode ser excluido");
        }
    }

    // @SuppressWarnings("uncheked")// quando estiver reclamando codigo em "amarelo" retorna lista tipada order
    public List<Endereco> filtrados(EnderecoFilter filtro) {
        Session session = manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Endereco.class);

        if (StringUtils.isNotBlank(filtro.getLogradouro())) {
            criteria.add(Restrictions.ilike("logradouro", filtro.getLogradouro(), MatchMode.ANYWHERE));//where queoque apareça antes ou depois "%joao%" //wherre nome like'%joao%'
        }
        return criteria.addOrder(Order.asc("logradouro")).list();
    }
}
