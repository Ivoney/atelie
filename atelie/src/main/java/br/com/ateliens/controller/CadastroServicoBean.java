package br.com.ateliens.controller;

import br.com.ateliens.model.Cliente;
import br.com.ateliens.model.FormaPagamento;
import br.com.ateliens.model.Servico;
import br.com.ateliens.model.StatusServicos;
import br.com.ateliens.repository.Clientes;
import br.com.ateliens.repository.Servicos;
import br.com.ateliens.service.CadastroServicos;
import br.com.ateliens.service.NegocioException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import javax.inject.Named;
import org.hibernate.validator.constraints.NotBlank;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@Named
@ViewScoped
public class CadastroServicoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CadastroServicos cadastro;

    @Inject
    private Clientes clientes;

    @Inject
    private Servicos servicos;

    private Servico servico;

    private List<Cliente> todosClientes;

    public void prepararCadastro() {
        this.todosClientes = this.clientes.todos();

        if (this.servico == null) {
            this.servico = new Servico();
        }
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            this.cadastro.salvar(this.servico);
            this.servico = new Servico();

            context.addMessage(null, new FacesMessage("Serviço salvo com sucesso!"));
        } catch (NegocioException e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }

    public void clienteSelecionado(SelectEvent event) {
        Cliente cliente = (Cliente) event.getObject();
        servico.setCliente(cliente);
    }

    public List<String> pesquisarDescricoes(String nomeServico) {
		return this.servicos.descricoesQueContem(nomeServico);
	}
    public FormaPagamento[] getTipoPgto() {
        return FormaPagamento.values();
    }

    public StatusServicos[] getTipoStatus() {
        return StatusServicos.values();
    }

    public List<Cliente> getTodosClientes() {
        return this.todosClientes;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    @NotBlank
    public String getNomeCliente() {
        return servico.getCliente() == null ? null : servico.getCliente().getNome();
    }

    public void setNomeCliente(String nome) {

    }
}
