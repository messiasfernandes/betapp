package br.com.bepapp.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.bepapp.domain.entidade.Produto;

public interface ProdutoQuery {
	Page<Produto> buscar(String paramentro, Pageable pageable);

}
