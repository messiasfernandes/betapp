
package br.com.bepapp.domain.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class Careteriticas  implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@NotEmpty
	@Column(length = 60)
	private String descricao;
	@JsonInclude(value = Include.NON_EMPTY)
	
	@JsonManagedReference
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "caracterisca", cascade = { CascadeType.ALL
			 }, orphanRemoval = true)
	
	private List <Atributo> atributo= new ArrayList<>();

}
