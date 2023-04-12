/**
 * 
 */
package br.com.bepapp.domain.entidade;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author messias
 *
 */
@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Componente extends GeradorId {

	
	private static final long serialVersionUID = 1L;
	@Digits(integer = 9, fraction = 3)
	private BigDecimal qte;
	@JsonIgnoreProperties(value = { "qtde", "precovenda","precocusto","customedio","imagemProduto","codigoEan13",
			 "caracteristica","atributos"
	}, allowGetters = true)
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private ProdutoVariacoes produtoVariacoes;
  
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Insumo insumo;
}
