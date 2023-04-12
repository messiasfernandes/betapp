package br.com.bepapp.model.dto;

import lombok.Data;

@Data
public class ProdutoLightDTO {
	private String nomeproduto;

	private String marca;

	private CategoriaDTO categoria = new CategoriaDTO();
}
