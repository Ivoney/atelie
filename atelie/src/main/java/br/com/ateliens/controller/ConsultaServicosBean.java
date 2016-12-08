package br.com.ateliens.controller;

import br.com.ateliens.model.Cliente;
import br.com.ateliens.model.Servico;
import br.com.ateliens.repository.Clientes;
import br.com.ateliens.repository.FiltroServico;

import br.com.ateliens.repository.Servicos;
import br.com.ateliens.service.CadastroServicos;
import br.com.ateliens.service.NegocioException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

@Named
@ViewScoped
public class ConsultaServicosBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private Servicos servicosRepository;
    @Inject
    private Clientes clientes;
    @Inject
    private CadastroServicos cadastro;
    private List<Servico> servicos;

    private Servico servicoSelecionado;
    
    public void excluir() {
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            this.cadastro.excluir(this.servicoSelecionado);
            this.consultar();

            context.addMessage(null, new FacesMessage("Servico excluído com sucesso!"));
        } catch (NegocioException e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }

    public void consultar() {
        this.servicos = servicosRepository.todos();
    }

    public List<Servico> getServico() {
        return servicos;
    }

    public Servico getServicoSelecionado() {
        return servicoSelecionado;
    }

    public void setServicoSelecionado(Servico servicoSelecionado) {
        this.servicoSelecionado = servicoSelecionado;
    }

   
}
