 
package br.com.ateliens.controller;
 
import br.com.ateliens.model.Funcionario;
import br.com.ateliens.model.Sexo;
import br.com.ateliens.model.StatusFuncionario;
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
public class CadastroFuncionarioBean implements Serializable{
   private static final long serialVersionUID = 1L;
   
   @Inject
   private CadastroFuncionarios cadastro;
   
   @Inject
   private Funcionarios funcionarios;
   
   private Funcionario funcionario;
   
   private List<Funcionario> todosFuncionarios;
   
   public void prepararCadastroFuncionario(){
       this.todosFuncionarios = this.funcionarios.todos();
       
       if(this.funcionario == null){
           this.funcionario = new Funcionario();
       }
       
   }
   
   public void salvar(){
       FacesContext context = FacesContext.getCurrentInstance();
       
       try{
           this.cadastro.salvar(this.funcionario);
           this.funcionario = new Funcionario();
           context.addMessage(null, new FacesMessage("Funcionário salvo com sucesso!"));
       }catch(NegocioException e){
           FacesMessage mensagem = new FacesMessage(e.getMessage());
           mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
           context.addMessage(null, mensagem);
       }
   }
   
   public Sexo[] getSexoTipo(){
       return Sexo.values();
   }
   public StatusFuncionario[] getStatus(){
       return StatusFuncionario.values();
   }
   public List<Funcionario> getTodosFuncionarios(){
       return this.todosFuncionarios;
   } 

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
   
}
