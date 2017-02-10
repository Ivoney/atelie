package br.com.ateliens.controller;

import br.com.ateliens.model.Grupo;
import br.com.ateliens.model.Perfil;
import br.com.ateliens.model.Usuario;
import br.com.ateliens.repository.Grupos;
import br.com.ateliens.repository.Usuarios;
import br.com.ateliens.security.UsuarioSistema;
import br.com.ateliens.service.CadastroUsuarios;
import br.com.ateliens.service.NegocioException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ExternalContext externalContext;

    @Inject
    private CadastroUsuarios cadastro;

    @Inject
    private Usuarios usuarios;

    private Usuario usuario = new Usuario();

    private List<Usuario> todosUsuarios = new ArrayList<>();

    @Inject
    private Grupos grupos;

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

    private UsuarioSistema getUsuarioLogado() {
        UsuarioSistema usuario = null;

        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

        if (auth != null && auth.getPrincipal() != null) {
            usuario = (UsuarioSistema) auth.getPrincipal();
        }

        return usuario;
    }

    public Perfil[] getTipoPerfil() {
        return Perfil.values();
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
