package br.com.bepapp.domain.entidade;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class ArquivoFoto {
	private String nomeArquivo;

	private String descricao;

	private String contentType;
	@Transient
	private String url;
	private Long tamanho;
}
