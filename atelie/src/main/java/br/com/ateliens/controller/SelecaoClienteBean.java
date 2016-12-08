package br.com.ateliens.controller;

import br.com.ateliens.model.Cliente;
import br.com.ateliens.repository.Clientes;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class SelecaoClienteBean implements Serializable {

    private static final long SerialVersionUID = 1L;

    @Inject
    private Clientes clientes;

    private String nome;

    private List<Cliente> clientesFiltrados;

    public void pesquisar() {
        clientesFiltrados = clientes.porNomeSemelhante(nome);
    }

    public void abrirDialogo() {
        Map<String, Object> opcoes = new HashMap<>();
        opcoes.put("modal", true);
        opcoes.put("resizable", false);
        opcoes.put("contentHeight", 470);

        RequestContext.getCurrentInstance().openDialog("SelecaoCliente", opcoes, null);
    }

    public void selecionar(Cliente cliente) {
        RequestContext.getCurrentInstance().closeDialog(cliente);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Cliente> getClientesFiltrados() {
        return clientesFiltrados;
    }
}
