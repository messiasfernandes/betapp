package br.com.bepapp.model.input;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class ProdutoInput {
    private Long id;
	private String nomeproduto;

	private String marca;

	private String unidade;
	private CategoriaInput categoria;
	private String imagemproduto;
	private List<ProdutoVariacaoInput> produtovariacoes = new ArrayList<>();

	@Override
	public String toString() {
		return "ProdutoInput [id=" + id + ", nomeproduto=" + nomeproduto + ", marca=" + marca + ", unidade=" + unidade
				+ ", categoria=" + categoria + ", produtovariacoes=" + produtovariacoes + "]";
	}
	                                 
}
