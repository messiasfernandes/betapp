package br.com.bepapp.query;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.bepapp.domain.entidade.Variacoes;
import br.com.bepapp.domain.service.ServiceFuncoes;

public class VariacaoQueryImpl extends ServiceFuncoes implements VariacaoQuery {
	@PersistenceContext
	EntityManager em;
	@Override
	public Page<Variacoes> buscar(String paramentro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Variacoes> criteria = builder.createQuery(Variacoes.class);
		Root<Variacoes> root = criteria.from(Variacoes.class);
	
	Predicate[] predicates = criarRestricoes(paramentro, builder, root);

	  root.fetch("atributo");
	
     criteria.select(root);
	 criteria.where(predicates);
	// criteria.orderBy(builder.asc(root.get("produto").get("nomeproduto")));
	
		TypedQuery<Variacoes> query = em.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(paramentro));
	}



	private Long total(String paramentro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Variacoes> root = criteria.from(Variacoes.class);

		criteria.where(criarRestricoes(paramentro, builder, root));

		criteria.select(builder.count(root));
		return em.createQuery(criteria).getSingleResult();
	}

	private Predicate[] criarRestricoes(String paramentro, CriteriaBuilder builder, Root<Variacoes> root) {
		List<Predicate> predicates = new ArrayList<>();
		if ((!ehnumero(paramentro) && (qtdecaraceteres(paramentro) >= 0))) {
			predicates.add(builder.or(builder.like(root.get("descricao"), "%"+ paramentro.toUpperCase() + "%")));
		}
		if ((ehnumero(paramentro))) {
	
			Long id = Sonumero(paramentro);
			predicates.add(builder.or(builder.equal(root.get("id"), id)));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

}
