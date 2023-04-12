package br.com.bepapp.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.bepapp.domain.entidade.ProdutoVariacoes;

public interface ProdutoVariacaoQuery {
	Page<ProdutoVariacoes> buscar(String paramentro, Pageable pageable);
	Page<ProdutoVariacoes>buscaid(Long codigo,Pageable pageable );

}
