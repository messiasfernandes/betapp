package br.com.bepapp.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bepapp.domain.entidade.Variacoes;
import br.com.bepapp.query.VariacaoQuery;
@Repository
public interface DaoVariacoes extends JpaRepository<Variacoes,Long>,VariacaoQuery {

}
