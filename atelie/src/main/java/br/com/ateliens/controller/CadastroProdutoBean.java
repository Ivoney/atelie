package br.com.ateliens.controller;

import br.com.ateliens.model.Produto;
import br.com.ateliens.model.Tipo;
import br.com.ateliens.repository.Produtos;
import br.com.ateliens.service.CadastroProduto;
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
public class CadastroProdutoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CadastroProduto cadastro;

    @Inject
    private Produtos produtos;

    private Produto produto;

    private List<Produto> todosProdutos;

    public void prepararCadastroProduto() {
        this.todosProdutos = this.produtos.todos();

        if (this.produto == null) {
            this.produto = new Produto();
        }
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            this.cadastro.salvar(this.produto);
            this.produto = new Produto();
            context.addMessage(null, new FacesMessage("Produto salvo com sucesso!"));
        } catch (NegocioException e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }

    public Tipo[] getTipo() {
        return Tipo.values();
    }

    public List<Produto> getTodosProdutos() {
        return this.todosProdutos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
