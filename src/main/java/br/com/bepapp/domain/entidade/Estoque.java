package br.com.bepapp.domain.entidade;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Estoque extends GeradorId {

	
	private static final long serialVersionUID = 1L;
	    @OneToOne(fetch = FetchType.LAZY)
	    @JoinColumn()
	    private Variants variant;
	    @Digits(integer = 9, fraction = 3)
	    private BigDecimal quantidade;

}
