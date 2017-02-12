package com.oliveira.pedidovenda.repository;

import com.oliveira.pedidovenda.model.Categoria;
import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Adriano de Oliveira
 */
public class Categorias implements Serializable {

    public static final long serialVersionUID = 0L;

    @Inject
    private EntityManager manager;

    public Categoria porId(Long id) {
        return manager.find(Categoria.class, id);
    }

    public List<Categoria> raizes() {
        return manager.createQuery("from Categoria where categoriaPai is null",
                Categoria.class).getResultList();

    }

    public List<Categoria> subcategoriasDe(Categoria categoriaPai) {

        return manager.createQuery("from Categoria where categoriaPai = :raiz", 
                Categoria.class).setParameter("raiz", categoriaPai).getResultList();
    }

}
