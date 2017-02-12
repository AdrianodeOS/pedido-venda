/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oliveira.pedidovenda.repository.filter;

import com.oliveira.pedidovenda.model.TipoPessoa;
import java.io.Serializable;

/**
 *
 * @author Adriano
 */
public class ClienteFilter implements Serializable {

    private static final long serialVersionUID = 0L;

    private String nome;
    private String email;
    private TipoPessoa[] tipoPessoa;
    private String documentoReceitaFederal;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocumentoReceitaFederal() {
        return documentoReceitaFederal;
    }

    public void setDocumentoReceitaFederal(String documentoReceitaFederal) {
        this.documentoReceitaFederal = documentoReceitaFederal;
    }

    public TipoPessoa[] getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa[] tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    

}
