package br.com.bepapp.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bepapp.domain.entidade.Produto;
import br.com.bepapp.query.ProdutoQuery;
@Repository
public interface DaoProduto extends JpaRepository<Produto, Long>,ProdutoQuery{

}
