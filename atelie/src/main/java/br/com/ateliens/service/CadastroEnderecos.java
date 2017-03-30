/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ateliens.service;

import br.com.ateliens.model.Endereco;
import br.com.ateliens.repository.Enderecos;
import br.com.ateliens.util.jpa.Transacional;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author Yvonei
 */
public class CadastroEnderecos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Enderecos enderecos;
    
    @Inject
    private Endereco endereco;
    
    public Endereco buscarEnderecoPor(String urlJson) {
        System.out.println("CHAMOU O SERVIÇO....");

        final GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();
        Gson g = new Gson();
        Endereco retornoEndereco = gson.fromJson(urlJson, Endereco.class);
        return retornoEndereco;

    }
    
    @Transacional
    public Endereco salvar(Endereco endereco) throws NegocioException{
        return this.enderecos.guardar(endereco);
    }
    
    @Transacional
    public void excluir(Endereco endereco) throws NegocioException{
        endereco = this.enderecos.porId(endereco.getId());
        this.enderecos.remover(endereco);
    }
}
