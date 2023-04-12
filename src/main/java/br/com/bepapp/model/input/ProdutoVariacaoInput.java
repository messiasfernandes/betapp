package br.com.bepapp.model.input;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ProdutoVariacaoInput {
	private Long id;

	private BigDecimal qtde;
	private BigDecimal precovenda;
	private BigDecimal precocusto;
	private BigDecimal customedio;

	private String sku;
	private String codigoEan13;
	private String caracteristica;
	private Boolean controlarestoque;
	private String codigofabricante;
	private VariacoesInput variacoes;
	// @JsonInclude(content = Include.NON_NULL)
	private List<ArquivoFotoInput> arquivos = new ArrayList<>();

}
