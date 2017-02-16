package com.oliveira.pedidovenda.model;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Produto.class)
public abstract class Produto_ {

	public static volatile SingularAttribute<Produto, Categoria> categoria;
	public static volatile SingularAttribute<Produto, String> nome;
	public static volatile SingularAttribute<Produto, Integer> quantidadeEstoque;
	public static volatile SingularAttribute<Produto, Long> id;
	public static volatile SingularAttribute<Produto, String> sku;
	public static volatile SingularAttribute<Produto, BigDecimal> valorUnitario;

}

