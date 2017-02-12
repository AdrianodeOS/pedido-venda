package com.oliveira.pedidovenda.repository;

import com.oliveira.pedidovenda.model.Grupo;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author Adriano
 */
public class Grupos implements Serializable {

	private static final long serialVersionUID = 0L;

	@Inject
	private EntityManager manager;

	public List<Grupo> grupos(){
		return manager.createQuery("FROM Grupo",Grupo.class).getResultList();
	}

	public  Grupo porId(Long id) {
		return this.manager.find(Grupo.class, id);
	}
	
	
	public Grupo grupoPorNome(String nome)
	{
		try
			{
			return manager.createQuery("from Grupo where upper(nome) = :nome", Grupo.class)
					.setParameter("nome", nome.toUpperCase())
					.getSingleResult();
			}
		catch (NoResultException e)
		{
			return null;
		}
	}
	
	
}
