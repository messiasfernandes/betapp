/**
 * 
 */
package br.com.bepapp.query;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import br.com.bepapp.domain.entidade.ProdutoVariacoes;

/**
 * @author messias
 *
 */
public class ProdutoVariacaoSpec {
	
	private List<Predicate> predicates = new ArrayList<>();
	
	public Specification<ProdutoVariacoes> buscar(String nome) {

		return (root, query, builder) -> {
			// root.fetch("componenentes", JoinType.LEFT);
				//root.fetch("produto").fetch("categoria");
	
		
			return builder.like(root.get("produto").get("nomeproduto"), "%" + nome + "%");
		//	return builder.or(predicates.toArray(new Predicate[0]));

		};

	}
	public Specification<ProdutoVariacoes> pesquisar(String nome) {

		return (root, query, builder) -> {


//			if (Long.class != query.getResultType()) {
//				root.fetch("produto").fetch("categoria");
//				 root.join("componenentes", JoinType.LEFT);
//				   predicates.add(builder.or( builder.like(root.get("produto").get("marca"), nome + "%"),
//					          builder.like(root.get("produto").get("categoria").get("nomecategoria"), nome + "%"),
//					          builder.like(root.get("produto").get("nomeproduto"), "%" + nome + "%"),
//					          builder.like(root.get("sku"), "%" + nome + "%")
//					          ));
//	        }
//			root.fetch("produto").fetch("categoria");
			 root.join("componenentes", JoinType.LEFT);
			   predicates.add(builder.or( builder.like(root.get("produto").get("marca"), nome + "%"),
				          builder.like(root.get("produto").get("categoria").get("nomecategoria"), nome + "%"),
				          builder.like(root.get("produto").get("nomeproduto"), "%" + nome + "%"),
				          builder.like(root.get("sku"), "%" + nome + "%")
				          ));
	//	predicates.add(builder.like(root.get("produto").get("categoria").get("nomecategoria"), nome + "%"));

	//	System.out.println( "tamanho 1"+ predicates.size());




///	query.where(predicates.toArray(new Predicate[0]));
			
			return builder.conjunction();
					//builder.or(predicates.toArray(new Predicate[0]));

		};
	
	}
	public Specification<ProdutoVariacoes> buscaid(Long codigo) {

		return (root, query, builder) -> {
			if (codigo != null) {
				if (codigo != null) {
					 root.fetch("componenentes", JoinType.LEFT);
						root.fetch("produto").fetch("categoria");;
					predicates.add(builder.equal(root.get("id"), codigo));
					   query.from(ProdutoVariacoes.class);
					  query.where(predicates.toArray(new Predicate[0]));

				}

			}
			return builder.or(predicates.toArray(new Predicate[0]));
		};
	}
	
	public Specification<ProdutoVariacoes> buscarEan(Long ean) {

		return (root, query, builder) -> {
			String nome = Long.toString(ean);
			if (StringUtils.hasText(nome)) {
				 root.fetch("componenentes", JoinType.LEFT);
					root.fetch("produto").fetch("categoria");;
				
				predicates.add(builder.like(root.get("codigoEan13"), nome + "%"));
			}
			return builder.or(predicates.toArray(new Predicate[0]));

		};

	}
}
