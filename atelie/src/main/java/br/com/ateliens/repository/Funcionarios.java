package br.com.ateliens.repository;

import br.com.ateliens.model.Funcionario;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class Funcionarios implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private EntityManager manager;
    
    public Funcionario porId(Long id) {
        return manager.find(Funcionario.class, id);
    }
    
    public List<Funcionario> todos() {
        TypedQuery<Funcionario> query = manager.createQuery("from Funcionario", Funcionario.class);
        return query.getResultList();
    }
    
    public void adicionar(Funcionario funcionario) {
        this.manager.persist(funcionario);
    }

    public Funcionario guardar(Funcionario funcionario) {
        return manager.merge(funcionario);
    }
    
    public void remover(Funcionario funcionario) {
        this.manager.remove(funcionario);
    }
}
