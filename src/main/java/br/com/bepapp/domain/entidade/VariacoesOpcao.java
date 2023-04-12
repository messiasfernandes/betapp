package br.com.bepapp.domain.entidade;

import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
///@Entity
public class VariacoesOpcao   {

	

	@ManyToOne
	private Atributo atributo;
	@ManyToOne
	private ProdutoVariacoes produtoVaricao;
	

}
