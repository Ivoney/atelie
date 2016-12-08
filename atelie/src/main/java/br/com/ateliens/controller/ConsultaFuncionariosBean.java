 
package br.com.ateliens.controller;

import br.com.ateliens.model.Funcionario;
import br.com.ateliens.repository.Funcionarios;
import br.com.ateliens.service.CadastroFuncionarios;
import br.com.ateliens.service.NegocioException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ConsultaFuncionariosBean implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Inject
    private Funcionarios funcionariosRepository; 
    
    @Inject
    private CadastroFuncionarios cadastro;
    
    private List<Funcionario> funcionarios;
    
    private Funcionario funcionarioSelecionado;
    
    public void excluir(){
        FacesContext context = FacesContext.getCurrentInstance();
        
        try{
            this.cadastro.excluir(this.funcionarioSelecionado);
            this.consultar();
            context.addMessage(null, new FacesMessage("Funcionario excluído com sucesso!"));
        }catch(NegocioException e){
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }
    
    public void consultar(){
        this.funcionarios = funcionariosRepository.todos();
    }
    
    public List<Funcionario> getFuncionario(){
        return funcionarios;
    }

    public Funcionario getFuncionarioSelecionado() {
        return funcionarioSelecionado;
    }

    public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
        this.funcionarioSelecionado = funcionarioSelecionado;
    }
    
    
    
}
