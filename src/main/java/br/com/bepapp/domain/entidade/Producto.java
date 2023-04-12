package br.com.bepapp.domain.entidade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Producto extends GeradorId {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String descricao;
	@Digits(integer = 9, fraction = 3)
	private BigDecimal preço;
	@JsonManagedReference
	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Variants> variacoes = new ArrayList<>();

}
