package br.com.bepapp.model.input;

import java.math.BigDecimal;

import br.com.bepapp.domain.entidade.Insumo;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class ComponeteInput {
	private Long id;
	private BigDecimal qte;
	private GradeInput grade;
	private Insumo insumo;
}
