package br.com.bepapp.domain.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Variacoes extends GeradorId {

	private static final long serialVersionUID = 1L;
	@NotEmpty
	@Column(length = 100)
	private String descricao;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Grade grade;
	@JsonInclude(value = Include.NON_EMPTY)
	@JsonManagedReference
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "variacoes", cascade =  CascadeType.ALL , orphanRemoval = true)
	private List<Atributo> atributo = new ArrayList<>();

}
