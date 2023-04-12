package br.com.bepapp.domain.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Carateristic extends GeradorId {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
@JsonManagedReference
	@OneToMany(mappedBy = "caracteristic", cascade = CascadeType.ALL)
	private List<Variants> variacoes = new ArrayList<>();

}
