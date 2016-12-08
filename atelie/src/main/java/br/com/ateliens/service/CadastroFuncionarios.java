 
package br.com.ateliens.service;

import br.com.ateliens.model.Funcionario;
import br.com.ateliens.repository.Funcionarios;
import br.com.ateliens.util.jpa.Transacional;
import java.io.Serializable;
import javax.inject.Inject;

 
public class CadastroFuncionarios implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Inject
    private Funcionarios funcionarios;
    
    @Transacional
    public Funcionario salvar(Funcionario funcionario) throws NegocioException{
        return this.funcionarios.guardar(funcionario);
    }
    
    @Transacional
    public void excluir (Funcionario funcionario) throws NegocioException{
        funcionario = this.funcionarios.porId(funcionario.getId());
        this.funcionarios.remover(funcionario);
    }
}
