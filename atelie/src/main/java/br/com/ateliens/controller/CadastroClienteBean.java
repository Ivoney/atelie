package br.com.ateliens.controller;

import br.com.ateliens.model.Cliente;
import br.com.ateliens.repository.Clientes;
import br.com.ateliens.service.CadastroClientes;
import br.com.ateliens.service.NegocioException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CadastroClientes cadastro;

    @Inject
    private Clientes clientes;

    private Cliente cliente;

    private List<Cliente> todosClientes;

    public void prepararCadastroCliente() {
        this.todosClientes = this.clientes.todos();

        if (this.cliente == null) {
            this.cliente = new Cliente();
        }
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.cadastro.salvar(this.cliente);
            this.cliente = new Cliente();
            context.addMessage(null, new FacesMessage("Cliente salvo com sucesso!"));
        } catch (NegocioException e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }

    public List<Cliente> getTodosCliente() {
        return this.todosClientes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
