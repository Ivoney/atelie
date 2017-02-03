/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ateliens.service;

import br.com.ateliens.model.Grupo;
import br.com.ateliens.repository.Grupos;
import br.com.ateliens.util.jpa.Transacional;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

 
public class CadastroGrupo implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private Grupos grupos;
    
    @Transacional
    public void salvar(Grupo grupo) throws NegocioException{
        this.grupos.guardar(grupo);
    }
    
    @Transacional
    public void excluir(Grupo grupo) throws NegocioException{
        grupo = this.grupos.porId(grupo.getId());
        this.grupos.remover(grupo);
    }
    
    @Transacional
    public List<Grupo> listar(){
        return grupos.todos();
    }
}
