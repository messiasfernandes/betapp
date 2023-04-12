package br.com.bepapp.model.input;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArquivoFotoInput {
	private String nomeArquivo;

	private String descricao;

	private String contentType;

	private String url;
	private Long tamanho;
}
