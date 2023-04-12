package br.com.bepapp.domain.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class ProdutoVariacoes extends GeradorId implements Serializable {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
	private static final long serialVersionUID = 1L;
	@JsonBackReference
	//@JsonIgnoreProperties(value = { "nomeproduto", "marca", "unidade" }, allowGetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Produto produto;

	@Digits(integer = 9, fraction = 3)

	private BigDecimal qtde;
	@Digits(integer = 9, fraction = 3)
	private BigDecimal precovenda;
	@Digits(integer = 9, fraction = 3)
	private BigDecimal precocusto;
	@Digits(integer = 9, fraction = 3)
	private BigDecimal customedio;

	@Column(length = 60)
	private String sku;

	@Column(length = 13)
	private String codigoEan13;
	@Column(length = 255)
	private String caracteristica;
	@Column(length = 13)
	private String codigofabricante;
	private Boolean controlarestoque;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(nullable = true )
	private Variacoes variacoes;
	//@JsonManagedReference

//	@Fetch(FetchMode.SUBSELECT)
//	@BatchSize(size = 500)
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "produtoVariacoes", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Componente> componentes = new ArrayList<>();
 
	@Fetch(FetchMode.SUBSELECT)
	@BatchSize(size = 500)
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "foto_produto", joinColumns = @JoinColumn(name = "prod_foto_id"))
	@Column(name = "fotos")
	private List<ArquivoFoto> arquivos = new ArrayList<>();

}
