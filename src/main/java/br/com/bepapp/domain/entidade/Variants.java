package br.com.bepapp.domain.entidade;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Variants extends GeradorId{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
	private Producto producto;
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private Carateristic caracteristic;
	
     private String valor;
     @OneToOne(mappedBy = "variant", cascade = CascadeType.ALL)
     private Estoque estoque;

}
