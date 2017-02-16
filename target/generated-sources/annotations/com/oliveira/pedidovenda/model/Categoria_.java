package com.oliveira.pedidovenda.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Categoria.class)
public abstract class Categoria_ {

	public static volatile ListAttribute<Categoria, Categoria> subcategorias;
	public static volatile SingularAttribute<Categoria, Long> id;
	public static volatile SingularAttribute<Categoria, Categoria> categoriaPai;
	public static volatile SingularAttribute<Categoria, String> descricao;

}

