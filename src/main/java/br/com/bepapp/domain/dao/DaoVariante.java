package br.com.bepapp.domain.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bepapp.domain.entidade.Variants;
@Repository
public interface DaoVariante extends JpaRepository<Variants, Long> {
    //List<Variants> findByProductoid(Long id);


}
