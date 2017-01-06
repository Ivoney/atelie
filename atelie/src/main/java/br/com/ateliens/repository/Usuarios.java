package br.com.ateliens.repository;

import br.com.ateliens.model.Usuario;
import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public Usuario porId(Long id) {
        return this.manager.find(Usuario.class, id);
    }
    
    public List<Usuario> todos(){
        TypedQuery<Usuario> query = manager.createQuery("from Usuario", Usuario.class);
        return query.getResultList();
    }
    
    public Usuario buscaPorEmail(String email) {
        Usuario usuario = null;

        try {
            usuario = this.manager.createQuery("from Usuario where lower(email) = :email", Usuario.class)
                    .setParameter("email", email.toLowerCase()).getSingleResult();
        } catch (NoResultException e) {
            // nenhum usuário encontrado com o e-mail informado
        }

        return usuario;
    }
    
    public void adicionar(Usuario usuario){
        this.manager.persist(usuario);
    }
    
    public Usuario guardar(Usuario usuario){
        return manager.merge(usuario);
    }

    public void remover(Usuario usuario){
        this.manager.remove(usuario);
    }
}
