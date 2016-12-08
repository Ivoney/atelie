/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package br.com.ateliens.controller;

import br.com.ateliens.model.Cliente;
import br.com.ateliens.model.Usuario;
import br.com.ateliens.repository.Usuarios;
import br.com.ateliens.service.CadastroUsuarios;
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
public class CadastroUsuarioBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CadastroUsuarios cadastro;

    @Inject
    private Usuarios usuarios;

    private Usuario usuario;

    private List<Usuario> todosUsuarios;

    public void prepararCadstroUsuario() {
        this.todosUsuarios = this.usuarios.todos();

        if (this.usuario == null) {
            this.usuario = new Usuario();
        }
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.cadastro.salvar(this.usuario);
            this.usuario = new Usuario();
            context.addMessage(null, new FacesMessage("Usuário salvo com sucesso!"));
        } catch (NegocioException e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }

    public List<Usuario> getTodosUsuarios() {
        return this.todosUsuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
*/