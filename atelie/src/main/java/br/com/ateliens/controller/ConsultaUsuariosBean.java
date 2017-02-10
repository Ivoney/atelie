/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ateliens.controller;

import br.com.ateliens.model.Grupo;
import br.com.ateliens.model.Usuario;
import br.com.ateliens.repository.Grupos;
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

/**
 *
 * @author Yvonei
 */
@Named
@ViewScoped
public class ConsultaUsuariosBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private Usuarios usuariosRepository;
    
    @Inject
    private CadastroUsuarios cadastro;
    
    private List<Usuario> usuarios;
    
    private Usuario usuarioSelecionado;
    
    
    public void excluir(){
         FacesContext context = FacesContext.getCurrentInstance();

        try {
            this.cadastro.excluir(this.usuarioSelecionado);
            this.consultar();

            context.addMessage(null, new FacesMessage("Usuário excluído com sucesso!"));
        } catch (NegocioException e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }
     public void consultar() {
        this.usuarios= usuariosRepository.todos();
    }

    public List<Usuario> getUsuario() {
        return usuarios;
    }
 
    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

}
