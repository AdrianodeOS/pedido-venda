package com.oliveira.pedidovenda.model.vo;

/**
 *
 * @author Adriano
 */
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DataValor implements Serializable {

    private static final long serialVersionUID = 0L;

    private Date data;
    private BigDecimal valor;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

}
