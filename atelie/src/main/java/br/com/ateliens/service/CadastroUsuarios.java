 
package br.com.ateliens.service;

import br.com.ateliens.model.Usuario;
import br.com.ateliens.repository.Usuarios;
import br.com.ateliens.util.jpa.Transacional;
import java.io.Serializable;
import java.util.Date;
import javax.inject.Inject;

public class CadastroUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private Usuarios usuarios;

    @Transacional
    public void salvar(Usuario usuario) throws NegocioException {
        this.usuarios.guardar(usuario);
    }

    @Transacional
    public void excluir(Usuario usuario) throws NegocioException {
        
        usuario = this.usuarios.porId(usuario.getId());
        this.usuarios.remover(usuario);
  
    }
    
   
}
