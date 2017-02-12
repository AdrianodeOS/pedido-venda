/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oliveira.pedidovenda.controller;

import com.oliveira.pedidovenda.model.Cliente;
import com.oliveira.pedidovenda.model.Endereco;
import com.oliveira.pedidovenda.model.TipoPessoa;
import com.oliveira.pedidovenda.service.CadastroClienteService;
import com.oliveira.pedidovenda.util.jsf.FacesUtil;
import java.io.Serializable;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Adriano de Oliveira
 */
@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

    private static final long serialVersionUID = 0L;

    @Inject
    private CadastroClienteService cadastroClienteService;

    @Produces
    @ClienteEdicao
    private Cliente cliente;
    private Endereco endereco;

    public CadastroClienteBean() {
        limpar();
    }

    public void salvar() {
        this.cliente = cadastroClienteService.salvar(this.cliente);
        limpar();
        FacesUtil.addInfoMessage("Cliente salvo com sucesso!");
    }

    public void adicionarEndereco() {
        endereco.setCliente(cliente);
        cliente.getEnderecos().add(endereco);
        endereco = new Endereco();
    }

    public void excluirEndereco() {
        cliente.getEnderecos().remove(endereco);
    }

    public Cliente getCliente() {
        if (cliente == null) {
            cliente = new Cliente();
        }
        return cliente;
    }

    private void limpar() {
        cliente = new Cliente();
        endereco = new Endereco();
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        if (cliente != null) {
            cliente.getEnderecos();
        }
    }

    public TipoPessoa[] getTiposPessoas() {
        return TipoPessoa.values();
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public boolean isEditando() {
        return this.cliente != null && this.cliente.getId() != null;
    }

    public boolean isPessoaFisica() {
        return cliente.getTipo() == null ? false : cliente.getTipo().equals(TipoPessoa.FISICA);
    }
    /*   public boolean isPessoaJuridica() {
        System.out.println("Chamei aqui " + this.cliente.getTipo().getDescricao());
        // return this.cliente.getTipo().equals(TipoPessoa.JURIDICA.getDescricao());
        return this.cliente.getTipo().getDescricao().equals(TipoPessoa.JURIDICA.getDescricao());
    }
   
    public boolean isEditando() {
        boolean resultado = false;
        if (this.cliente != null) {
            resultado = this.cliente.getId() != null;
        }
        return resultado;
    }*/
}
