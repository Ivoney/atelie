/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ateliens.controller;

import br.com.ateliens.model.Cliente;
import br.com.ateliens.repository.Clientes;
import br.com.ateliens.repository.ClientesFiltrados;
import br.com.ateliens.repository.FiltroCliente;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

@Named
@ViewScoped
public class PesquisaClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ClientesFiltrados clientes;
    private FiltroCliente filtro = new FiltroCliente();
    private LazyDataModel<Cliente> model;

    public PesquisaClienteBean() {
        model = new LazyDataModel<Cliente>() {

            private static final long serialVersionUID = 1L;

            @Override
            public List<Cliente> load(int first, int pageSize,
                    String sortField, SortOrder sortOrder,
                    Map<String, Object> filters) {

                filtro.setPrimeiroRegistro(first);
                filtro.setQuantidadeRegistros(pageSize);
                filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
                filtro.setPropriedadeOrdenacao(sortField);
                setRowCount(clientes.quantidadeFiltrados(filtro));
                return clientes.filtrados(filtro);
            }

        };
    }
    public FiltroCliente getFiltro() {
        return filtro;
    }

    public LazyDataModel<Cliente> getModel() {
        return model;
    }

}