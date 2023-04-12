package br.com.bepapp.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value = Include.NON_EMPTY)
public class ProdutoVariacaoDto {
    private Long id;
//	private ProdutoDTO produto;

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
