package br.com.bepapp.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bepapp.domain.entidade.Componente;


@Repository
public interface DaoComponte extends JpaRepository<Componente, Long> {

}
