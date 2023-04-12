package br.com.bepapp.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class ProdutocomVariacoesDTO {
	
	  private Long id;
     private ProdutoLightDTO produto;

		private BigDecimal qtde;

		private BigDecimal precovenda;

		private BigDecimal precocusto;

		private BigDecimal customedio;

		private String imagemProduto;
		private String codigoEan13;
		private String sku;
		private String caracteristica;
		private Boolean controlarestoque ;
		 private String codigofabricante;
		private VariacoesDTO variacoes;

		private List<ComponenteDTO> componenentes = new ArrayList<>();
		private List<ArquivoDto> arquivos = new ArrayList<>();

}
