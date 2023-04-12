package br.com.bepapp.model.input;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InsumoInput {
	private Long id;
	private ProdutoVariacaoInput produtoVariacoes;
}
