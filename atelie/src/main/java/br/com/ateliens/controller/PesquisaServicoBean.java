/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ateliens.controller;

import br.com.ateliens.model.Servico;
import br.com.ateliens.repository.FiltroServico;
import br.com.ateliens.repository.Servicos;
import br.com.ateliens.repository.ServicosFiltrados;
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
public class PesquisaServicoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ServicosFiltrados servicos;

    private FiltroServico filtro = new FiltroServico();

    private LazyDataModel<Servico> model;

    public PesquisaServicoBean() {
        model = new LazyDataModel<Servico>() {

            private static final long serialVersionUID = 1L;

            @Override
            public List<Servico> load(int first, int pageSize,
                    String sortField, SortOrder sortOrder,
                    Map<String, Object> filters) {

                filtro.setPrimeiroRegistro(first);
                filtro.setQuantidadeRegistros(pageSize);
                filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
                filtro.setPropriedadeOrdenacao(sortField);

                setRowCount(servicos.quantidadeFiltrados(filtro));

                return servicos.filtrados(filtro);
            }

        };
    }

    public FiltroServico getFiltro() {
        return filtro;
    }

    public LazyDataModel<Servico> getModel() {
        return model;
    }

}
