/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ateliens.repository;

import br.com.ateliens.model.Endereco;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Yvonei
 */
public class Enderecos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;
    
    @Inject
    public Enderecos(EntityManager manager){
        this.manager = manager;
    }
    
    public Endereco porId(Long id){
        return manager.find(Endereco.class, id);
    }

    public List<Endereco> todos(){
        TypedQuery<Endereco> query = manager.createQuery("from Endereco", Endereco.class);
        return query.getResultList();
    } 
    
    public void adicionar(Endereco endereco){
        this.manager.persist(endereco);
    }
    
    public Endereco guardar(Endereco endereco){
        return manager.merge(endereco);
    }
    
    public void remover(Endereco endereco){
        this.manager.remove(endereco);
    }
}
