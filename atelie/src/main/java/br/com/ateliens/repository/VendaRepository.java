package br.com.ateliens.repository;

import br.com.ateliens.model.FormaPagamento;
import br.com.ateliens.model.Venda;
import br.com.ateliens.service.NegocioException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.primefaces.model.chart.PieChartModel;

 
public class VendaRepository implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Inject
    private EntityManager manager;
    
    public Venda poId(Long id){
        return manager.find(Venda.class, id);
    }
    
    public List<Venda> todosTiposVendas(){
        return manager.createQuery("from Venda",Venda.class).getResultList();    
    }
    
    public Venda venda(Venda venda){
        for(int i = 0; i < this.todosTiposVendas().size(); i++){
            if(venda == this.todosTiposVendas().get(i)){
                return this.todosTiposVendas().get(i);
            }
        }
        return null;
    }
    
} 
