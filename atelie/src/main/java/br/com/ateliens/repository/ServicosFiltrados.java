package br.com.ateliens.repository;

import br.com.ateliens.model.Cliente;
import br.com.ateliens.model.Servico;
import br.com.ateliens.service.NegocioException;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class ServicosFiltrados implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;
    
    @SuppressWarnings("unchecked")
    public List<Servico> filtrados(FiltroServico filtro) {
        Criteria criteria = criarCriteriaParaFiltro(filtro);

        criteria.setFirstResult(filtro.getPrimeiroRegistro());
        criteria.setMaxResults(filtro.getQuantidadeRegistros());

        if (filtro.isAscendente() && filtro.getPropriedadeOrdenacao() != null) {
            criteria.addOrder(Order.asc(filtro.getPropriedadeOrdenacao()));
        } else if (filtro.getPropriedadeOrdenacao() != null) {
            criteria.addOrder(Order.desc(filtro.getPropriedadeOrdenacao()));
        }
        return criteria.list();
    }

    public int quantidadeFiltrados(FiltroServico filtro) {
        Criteria criteria = criarCriteriaParaFiltro(filtro);

        criteria.setProjection(Projections.rowCount());

        return ((Number) criteria.uniqueResult()).intValue();
    }

    private Criteria criarCriteriaParaFiltro(FiltroServico filtro) {
        Session session = manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Servico.class);

        if (StringUtils.isNotEmpty(filtro.getNomeServico())) {
            criteria.add(Restrictions.ilike("nomeServico", filtro.getNomeServico(), MatchMode.ANYWHERE));
        }
        return criteria;
    }
}
