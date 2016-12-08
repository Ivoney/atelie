 
package br.com.ateliens.service;

import br.com.ateliens.model.Produto;
import br.com.ateliens.repository.Produtos;
import br.com.ateliens.util.jpa.Transacional;
import java.io.Serializable;
import javax.inject.Inject;

public class CadastroProduto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private Produtos produtos;
    
    @Transacional
    public void salvar(Produto produto) throws NegocioException{
        this.produtos.guardar(produto);
    }
    
    @Transacional
    public void excluir(Produto produto) throws NegocioException{
        produto = this.produtos.porId(produto.getId());
        this.produtos.remover(produto);
    }
}
