package br.com.ateliens.repository;

import br.com.ateliens.model.Cliente;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    @Inject
    public Clientes(EntityManager manager) {
        this.manager = manager;
    }

    public Cliente porId(Long id) {
        return manager.find(Cliente.class, id);
    }

    public List<Cliente> todos() {
        TypedQuery<Cliente> query = manager.createQuery("from Cliente", Cliente.class);
        return query.getResultList();
    }

    public List<Cliente> porNomeSemelhante(String nome) {
        return manager.createQuery("from Cliente where nome like :nome", Cliente.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    public void adicionar(Cliente cliente) {
        this.manager.persist(cliente);
    }

    public Cliente guardar(Cliente cliente) {
        return manager.merge(cliente);
    }

    public void remover(Cliente cliente) {
 
        this.manager.remove(cliente);

         
    }
}
