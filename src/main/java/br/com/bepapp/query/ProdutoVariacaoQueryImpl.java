package br.com.bepapp.query;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.bepapp.domain.entidade.ProdutoVariacoes;
import br.com.bepapp.domain.service.ServiceFuncoes;

public class ProdutoVariacaoQueryImpl extends ServiceFuncoes implements ProdutoVariacaoQuery {
	@PersistenceContext
	EntityManager em;

	@Override
	public Page<ProdutoVariacoes> buscar(String paramentro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ProdutoVariacoes> criteria = builder.createQuery(ProdutoVariacoes.class);
		Root<ProdutoVariacoes> root = criteria.from(ProdutoVariacoes.class);
	
	Predicate[] predicates = criarRestricoes(paramentro, builder, root);

	  root.fetch("variacoes");
	root.fetch("componenentes", JoinType.LEFT).fetch("insumo", JoinType.LEFT);
    root.fetch("produto").fetch("categoria");
     criteria.select(root);
	 criteria.where(predicates);
	 criteria.orderBy(builder.asc(root.get("produto").get("nomeproduto")));
	
		TypedQuery<ProdutoVariacoes> query = em.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(paramentro));
	}

	@Override
	public Page<ProdutoVariacoes> buscaid(Long codigo, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ProdutoVariacoes> criteria = builder.createQuery(ProdutoVariacoes.class);
		Root<ProdutoVariacoes> root = criteria.from(ProdutoVariacoes.class);


		root.fetch("produto").fetch("categoria");
		root.fetch("componenentes", JoinType.LEFT);

		criteria.select(root);
		criteria.where(criarRestricoes(codigo.toString(), builder, root));

		TypedQuery<ProdutoVariacoes> query = em.createQuery(criteria);

		return new PageImpl<>(query.getResultList(), pageable, total(codigo.toString()));
	}

	private Long total(String paramentro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<ProdutoVariacoes> root = criteria.from(ProdutoVariacoes.class);

		criteria.where(criarRestricoes(paramentro, builder, root));

		criteria.select(builder.count(root));
		return em.createQuery(criteria).getSingleResult();
	}

	private Predicate[] criarRestricoes(String paramentro, CriteriaBuilder builder, Root<ProdutoVariacoes> root) {
		List<Predicate> predicates = new ArrayList<>();
		if ((!ehnumero(paramentro) && (qtdecaraceteres(paramentro) >= 0))) {
			predicates.add(builder.or(builder.like(root.get("produto").get("marca"), paramentro + "%"),
					builder.like(root.get("produto").get("categoria").get("nomecategoria"), paramentro + "%"),
					builder.like(root.get("produto").get("nomeproduto"), "%" + paramentro + "%"),
					builder.like(root.get("sku"), "%" + paramentro + "%")));
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
