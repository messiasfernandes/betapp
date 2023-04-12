package br.com.bepapp.model.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class ProdutoDTO {
	
    private Long id;
	private String nomeproduto;
	private String marca;
	private String unidade;
	private String imagemproduto;
	private CategoriaDTO categoria;
	private List<ProdutoVariacaoDto> produtovariacoes = new ArrayList<>();

}
