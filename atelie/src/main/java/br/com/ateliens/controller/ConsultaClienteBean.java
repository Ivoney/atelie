package br.com.ateliens.controller;
import br.com.ateliens.model.Cliente;
import br.com.ateliens.repository.Clientes;
import br.com.ateliens.service.CadastroClientes;
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
public class ConsultaClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private Clientes clientesRepository;
    @Inject
    private CadastroClientes cadastro;
    private List<Cliente> clientes;
    private Cliente clienteSelecionado;
    
    public void excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            
            this.cadastro.excluir(this.clienteSelecionado);
            this.consultar();

            context.addMessage(null, new FacesMessage("Cliente excluído com sucesso!"));
        } catch (NegocioException | javax.persistence.RollbackException e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
           context.addMessage(null, new FacesMessage("Esse cliente não pode ser excluído porque está sendo usado!"));
        } 
    }
    
    public void consultar() {
        this.clientes = clientesRepository.todos();
    }

    public List<Cliente> getCliente() {
        return clientes;
    }
    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }
    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }       
}
