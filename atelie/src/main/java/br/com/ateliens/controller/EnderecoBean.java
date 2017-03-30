/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ateliens.controller;

import br.com.ateliens.model.Endereco;
import br.com.ateliens.model.Servico;
import br.com.ateliens.repository.Enderecos;
import br.com.ateliens.service.CadastroEnderecos;
import br.com.ateliens.service.NegocioException;
 
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Yvonei
 */ 
@Named
@SessionScoped
public class EnderecoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private CadastroEnderecos cadastro;

    @Inject
    private Enderecos enderecos;
    
    private List<Endereco> listagem = new ArrayList<>();
    
    private List<Endereco> todosEnderecos;

    private Endereco endereco;

    private String cep;
    
    public void prepararCadastro(){
        this.todosEnderecos = this.enderecos.todos();
        
        if(this.endereco == null){
            this.endereco = new Endereco();
        }
        
    }
    
       public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            this.cadastro.salvar(this.endereco);
            this.endereco = new Endereco();

            context.addMessage(null, new FacesMessage("Endereco salvo com sucesso!"));
        } catch (NegocioException e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }

    //private CadastroEnderecos cadastro = new CadastroEnderecos();

    //Retorna o endereco propriamente dito
    public Endereco carregaEndereco() {
        endereco = new Endereco();
        Client c = Client.create();
        WebResource wr = c.resource("http://viacep.com.br/ws/" + this.getCep() + "/json/");
        System.out.println("Chamou o URI....");
        this.endereco = cadastro.buscarEnderecoPor(wr.get(String.class));
        String JSON = wr.get(String.class);
        System.out.println(JSON);
        return this.getEndereco();

    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Endereco> getListagem() {
        return listagem;
    }

    public void setListagem(List<Endereco> listagem) {
        this.listagem = listagem;
    }

    public void limpar() {
        this.endereco = new Endereco();
    }
    
    public List<Endereco> getTodosEnderecos(){
        return this.todosEnderecos;
    }
    

}
