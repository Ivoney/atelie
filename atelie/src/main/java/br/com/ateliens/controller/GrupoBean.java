/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ateliens.controller;

import br.com.ateliens.model.Grupo;
import br.com.ateliens.repository.Grupos;
import br.com.ateliens.service.CadastroGrupo;
import br.com.ateliens.service.NegocioException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class GrupoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private CadastroGrupo cadastro;
    
    @Inject
    private Grupos grupos;
   
    private Grupo grupo = new Grupo();
    
    private List<Grupo> todosGrupos;
    
    public void prepararCadastroGrupo(){
        this.todosGrupos = this.grupos.todos();
        
        if(this.grupo == null){
            this.grupo = new Grupo();
        }
    }
    
    public void salvar(){
        FacesContext context = FacesContext.getCurrentInstance();
        
        try{
            this.cadastro.salvar(this.grupo);
            this.grupo = new Grupo();
            context.addMessage(null, new FacesMessage("Grupo salvo com sucesso!"));
        }catch (NegocioException e){
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }
    
    public List<Grupo> getTodosGrupos(){
        return this.todosGrupos;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    
    
}
