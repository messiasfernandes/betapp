package br.com.bepapp.domain.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Grade extends GeradorId {

	private static final long serialVersionUID = 1L;
	@Column(length = 120)
	private String descricao;

}
