package br.com.ateliens.service;

import br.com.ateliens.model.Servico;
import br.com.ateliens.model.StatusServicos;
import br.com.ateliens.repository.Servicos;
import br.com.ateliens.util.jpa.Transacional;
import java.io.Serializable;
import java.util.Date;
import javax.inject.Inject;

public class CadastroServicos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Servicos servicos;

    @Transacional
    public void salvar(Servico servico) throws NegocioException {
        if (servico.getDataEntrada() != null && servico.getDataEntrada().after(new Date())) {
            throw new NegocioException("Data de entrada não pode ser uma data futura");

        }
        this.servicos.guardar(servico);
    }

    @Transacional
    public void excluir(Servico servico) throws NegocioException {
        
        servico = this.servicos.porId(servico.getId());
        
         
        this.servicos.remover(servico);
    }
}
