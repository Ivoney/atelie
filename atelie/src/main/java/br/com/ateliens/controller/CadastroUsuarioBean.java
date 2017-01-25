package br.com.ateliens.controller;

import br.com.ateliens.model.Grupo;
import br.com.ateliens.model.StatusUsuarios;
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

    private Grupo grupo;
    
    private List<Grupo> listaGrupos;

    private List<Usuario> todosUsuarios;

    public void prepararCadastroUsuario() {
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

    public StatusUsuarios[] getStatus() {
        return StatusUsuarios.values();
    }
    
       public List<Grupo> getListaGrupos() {
        return this.usuario.getGrupos();
    }

    public void setListaGrupos(List<Grupo> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    
}
