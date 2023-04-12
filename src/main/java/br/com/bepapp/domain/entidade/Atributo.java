/**
 * 
 */
package br.com.bepapp.domain.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author messias
 *
 */
@Getter
@Setter
@Entity
public class Atributo  extends GeradorId  {
	
	private static final long serialVersionUID = 1L;
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private Variacoes variacoes;
	@Setter(value = AccessLevel.NONE)
	@Column(length = 60)
	private String tipo;
	@Setter(value = AccessLevel.NONE)

	@Column(length = 60)
	private String valor;
	public void setTipo(String tipo) {
		this.tipo = tipo.toUpperCase();
	}
	public void setValor(String valor) {
		this.valor = valor.toUpperCase();
	}
	

}
