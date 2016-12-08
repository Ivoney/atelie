 
package br.com.ateliens.repository;
 
import br.com.ateliens.model.Produto;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class Produtos implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Inject
    private EntityManager manager;
    
    public Produto porId(Long id){
        return manager.find(Produto.class, id);
    }
    
    public List<Produto> todos(){
        TypedQuery<Produto> query = manager.createQuery("from Produto", Produto.class);
        return query.getResultList();
    }
    
    public void adicionar(Produto produto){
        this.manager.persist(produto);
    }
    
    public Produto guardar(Produto produto){
        return manager.merge(produto);
    }
    
    public void remover(Produto produto){
        this.manager.remove(produto);
    }
}
