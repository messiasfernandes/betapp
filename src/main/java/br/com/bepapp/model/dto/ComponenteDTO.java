package br.com.bepapp.model.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class ComponenteDTO {
	private Long id;
	private BigDecimal qte;
	//private ProdutoVaraicaoLightDTO produtoVariacoes = new ProdutoVaraicaoLightDTO();
	private IsumoDTO  insumo;

}
