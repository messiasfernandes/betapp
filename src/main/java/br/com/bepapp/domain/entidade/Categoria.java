/**
 * 
 */
package br.com.bepapp.domain.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;

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
public class Categoria extends GeradorId {

	private static final long serialVersionUID = 1L;

	@Setter(value = AccessLevel.NONE)
	
	@Column(length = 50)
	private String nomecategoria;

	public void setNomecategoria(String nomecategoria) {
		if (nomecategoria!=null) {
			this.nomecategoria = nomecategoria.toUpperCase();
		} else {
			this.nomecategoria = nomecategoria;
		}
	}

	@Override
	public String toString() {
		return "Categoria [nomecategoria=" + nomecategoria + "]";
	}

}
