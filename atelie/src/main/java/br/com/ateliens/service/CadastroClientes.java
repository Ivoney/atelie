package br.com.ateliens.service;

import br.com.ateliens.model.Cliente;
import br.com.ateliens.repository.Clientes;
import br.com.ateliens.util.jpa.Transacional;
import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.RollbackException;

public class CadastroClientes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Clientes clientes;

    @Inject
    private Cliente cliente;

    @Transacional
    public Cliente salvar(Cliente cliente) throws NegocioException {
        return this.clientes.guardar(cliente);
    }

    @Transacional
    public void excluir(Cliente cliente) throws NegocioException  {
        cliente = this.clientes.porId(cliente.getId());
        this.clientes.remover(cliente);
        
       
    }
}

/*@Transacional
    public boolean salvar(Cliente cliente) throws NegocioException{
        boolean existe = true;
        
        if(!clientes.todos().equals(this.cliente.getNome())){
            throw new NegocioException("Cliente já cadastrado");
          
        }else{
            existe = true;
        }
        return existe;
    }*/
 /*@Transactional
	public void excluir(Lancamento lancamento) throws NegocioException {
		lancamento = this.lancamentos.porId(lancamento.getId());
		
		if (lancamento.getDataPagamento() != null) {
			throw new NegocioException("Não é possível excluir um lançamento pago!");
		}
		
		this.lancamentos.remover(lancamento);
	}*/
