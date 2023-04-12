package br.com.bepapp.model.input;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class VariacoesInput {
 
	private Long id;
	private GradeInput grade;
	private String descricao;
	@JsonInclude(value = Include.NON_EMPTY)
	private List <AtributoInput> atributo= new ArrayList<>();
}
