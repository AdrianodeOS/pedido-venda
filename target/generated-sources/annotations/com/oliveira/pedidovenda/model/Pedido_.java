package com.oliveira.pedidovenda.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pedido.class)
public abstract class Pedido_ {

	public static volatile SingularAttribute<Pedido, FormaPagamento> formaPagamento;
	public static volatile SingularAttribute<Pedido, String> observacao;
	public static volatile SingularAttribute<Pedido, Usuario> vendedor;
	public static volatile ListAttribute<Pedido, ItemPedido> itens;
	public static volatile SingularAttribute<Pedido, BigDecimal> valorDesconto;
	public static volatile SingularAttribute<Pedido, EnderecoEntrega> enderecoEntrega;
	public static volatile SingularAttribute<Pedido, Cliente> cliente;
	public static volatile SingularAttribute<Pedido, BigDecimal> valorFrete;
	public static volatile SingularAttribute<Pedido, Date> dataEntrega;
	public static volatile SingularAttribute<Pedido, BigDecimal> valorTotal;
	public static volatile SingularAttribute<Pedido, Long> id;
	public static volatile SingularAttribute<Pedido, Date> dataCriacao;
	public static volatile SingularAttribute<Pedido, StatusPedido> status;

}

