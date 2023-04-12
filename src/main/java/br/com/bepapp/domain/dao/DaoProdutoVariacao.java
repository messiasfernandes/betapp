package br.com.bepapp.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.bepapp.domain.entidade.ProdutoVariacoes;
import br.com.bepapp.query.ProdutoVariacaoQuery;


@Repository
public interface DaoProdutoVariacao extends JpaRepository<ProdutoVariacoes, Long>,JpaSpecificationExecutor<ProdutoVariacoes>, ProdutoVariacaoQuery{

}
