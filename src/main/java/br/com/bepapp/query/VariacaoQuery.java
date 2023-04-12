package br.com.bepapp.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.bepapp.domain.entidade.Variacoes;

public interface VariacaoQuery {
	
	Page<Variacoes> buscar(String paramentro, Pageable pageable);

}
