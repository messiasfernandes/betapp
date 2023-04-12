package br.com.bepapp.model.input;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaInput {
	private Long id;
	@NotEmpty
	@NotNull
	private String nomecategoria;

}
