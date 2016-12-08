package br.com.ateliens.repository;
import br.com.ateliens.model.Servico;
import br.com.ateliens.service.NegocioException;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class Servicos implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public Servico porId(Long id) {
        return manager.find(Servico.class, id);
    }

    public List<Servico> todos() {
        TypedQuery<Servico> query = manager.createQuery("from Servico", Servico.class);
        return query.getResultList();
    }

    public List<String> descricoesQueContem(String nomeServico) {
        TypedQuery<String> query = manager.createQuery(
                "select distinct nomeServico from Servico "
                + "where upper(nomeServico) like upper(:nomeServico)",
                String.class);
        query.setParameter("nomeServico", "%" + nomeServico + "%");
        return query.getResultList();
    }

    public void adicionar(Servico servico) {
        this.manager.persist(servico);
    }

    public Servico guardar(Servico servico) {
        return manager.merge(servico);
    }

    public void remover(Servico servico) throws NegocioException {
        
        if (servico.getStatus().PENDENTE.equals(this)) {
            throw new NegocioException("Não pode");
        }
        this.manager.remove(servico);
    }

}
