package com.oliveira.pedidovenda.service;

import com.oliveira.pedidovenda.model.Cliente;
import com.oliveira.pedidovenda.repository.Clientes;
import com.oliveira.pedidovenda.util.jpa.Transactional;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author Adriano
 */
public class CadastroClienteService implements Serializable {

    private static final long serialVersionUID = 0L;

    @Inject
    private Clientes clientes;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        Cliente clienteExistente = clientes.porDocumento(cliente.getDocumentoReceitaFederal());
        if (clienteExistente != null && !clienteExistente.equals(cliente)) {
            throw new NegocioException("Já existe um cliente com o documento informado");
        }
       return clientes.guardar(cliente);
    }

}
