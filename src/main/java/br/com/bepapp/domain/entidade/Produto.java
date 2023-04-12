/**
 * 
 */
package br.com.bepapp.domain.entidade;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author messias
 *
 */
/**
 * @author messias
 *
 */
@Setter
@Getter
@Entity
//@JsonIgnoreProperties(ignoreUnknown = false)
public class Produto extends GeradorId implements Serializable {

	private static final long serialVersionUID = 1L;
	@Setter(value = AccessLevel.NONE)
	@NotEmpty
	@Column(length = 120)
	private String nomeproduto;
	@Setter(value = AccessLevel.NONE)
	@Column(length = 60)
	private String marca;
	@Column(length = 3)
	private String unidade;
	private String imagemproduto;
	@JsonIgnoreProperties(value = {  "nomecategoria" }, allowGetters = true)

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Categoria categoria;
    @JsonManagedReference
	@Fetch(FetchMode.SUBSELECT)
//	@BatchSize(size = 500)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProdutoVariacoes> produtovariacoes = new ArrayList<>();

	public void setNomeproduto(String nomeproduto) {
		if(nomeproduto!=null) {
			this.nomeproduto = nomeproduto.toUpperCase();
		}else {
			this.nomeproduto = nomeproduto;
		}
	
	}

	public void setMarca(String marca) {
		if (marca != null) {
			this.marca = marca.toUpperCase();
		} else {
			this.marca = marca;
		}
	}

	@Override
	public String toString() {
		return "Produto [nomeproduto=" + nomeproduto + ", marca=" + marca + ", unidade=" + unidade + ", categoria="
				+ categoria + ", produtovariacoes=" + produtovariacoes + "]";
	}

}
