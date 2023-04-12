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

import br.com.bepapp.domain.entidade.Produto;

import br.com.bepapp.domain.service.ServiceFuncoes;

public class ProdutoQueryImpl extends ServiceFuncoes implements ProdutoQuery {
	@PersistenceContext
	EntityManager em;

	@Override
	public Page<Produto> buscar(String paramentro, Pageable pageable) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> criteria = builder.createQuery(Produto.class);
		Root<Produto> root = criteria.from(Produto.class);

		Predicate[] predicates = criarRestricoes(paramentro, builder, root);

		root.fetch("categoria");
		root.fetch("produtovariacoes", JoinType.LEFT).fetch("variacoes", JoinType.LEFT);
		// .fetch("componenentes", JoinType.LEFT).fetch("insumo", JoinType.LEFT);
		criteria.select(root);
		criteria.where(predicates);
		criteria.orderBy(builder.asc(root.get("nomeproduto")));
		TypedQuery<Produto> query = em.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(paramentro));
	}

	private Long total(String paramentro) {
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Produto> root = criteria.from(Produto.class);

		criteria.where(criarRestricoes(paramentro, builder, root));

		criteria.select(builder.count(root));
		return em.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Produto> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);

	}

	private Predicate[] criarRestricoes(String paramentro, CriteriaBuilder builder, Root<Produto> root) {
		List<Predicate> predicates = new ArrayList<>();

		if ((!ehnumero(paramentro) && (qtdecaraceteres(paramentro) >= 0))) {
			predicates.add(builder.or(builder.like(root.get("marca"), paramentro + "%"),
					builder.like(root.get("categoria").get("nomecategoria"), paramentro + "%"),
					builder.like(root.get("nomeproduto"), "%" + paramentro + "%")

			)

			);
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
