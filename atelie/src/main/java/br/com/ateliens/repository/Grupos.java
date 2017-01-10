/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ateliens.repository;

import br.com.ateliens.model.Grupo;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Yvonei
 */
public class Grupos implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Inject
    private EntityManager manager;
    
    public Grupo porId(Long id){
        return this.manager.find(Grupo.class, id);
    }
    
    public List<Grupo> todos(){
        TypedQuery<Grupo> query = manager.createQuery("FROM Grupo",Grupo.class);
        return query.getResultList();
    }
    
   public void adicionar(Grupo grupo){
       this.manager.persist(grupo);
   }
   
   public Grupo guardar(Grupo grupo){
       return manager.merge(grupo);
   }
   
   public void remover(Grupo grupo){
       this.manager.remove(grupo);
   }
}
