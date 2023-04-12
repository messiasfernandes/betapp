package br.com.bepapp.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.bepapp.domain.entidade.Atributo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VariacoesDTO {
	private Long id;
	private GradeDTO grade;

	private String descricao;

	@JsonInclude(value = Include.NON_EMPTY)
	private List<Atributo> atributo = new ArrayList<>();
}
