/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ateliens.controller;

import br.com.ateliens.model.Produto;
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

/**
 *
 * @author Yvonei
 */
@Named
@ViewScoped
public class ConsultaProdutoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private Produtos produtoRepository;

    @Inject
    private CadastroProduto cadastro;

    private List<Produto> produtos;

    private Produto produtoSelecionado;

    public void excluir() {
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            this.cadastro.excluir(this.produtoSelecionado);
            this.consultar();
            context.addMessage(null, new FacesMessage("Produto excluído com sucesso!"));
        } catch (NegocioException e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }

    public void consultar() {
        this.produtos = produtoRepository.todos();
    }

    public List<Produto> getProduto() {
        return produtos;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }

}
