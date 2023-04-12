package br.com.bepapp.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.bepapp.domain.entidade.Variacoes;
import br.com.bepapp.model.dto.VariacoesDTO;
import br.com.bepapp.model.input.VariacoesInput;
@Component
public class VariacaoConverter  {
	
	@Autowired
	private ModelMapper modelMapper;

	public VariacoesDTO toDto(Variacoes objeto) {

		return modelMapper.map(objeto, VariacoesDTO.class);
	}
     public Variacoes toEntity(VariacoesInput objeto) {
		
		return modelMapper.map(objeto, Variacoes.class);
	}

	public Page<VariacoesDTO> topage(Page<Variacoes> objetos) {

		return objetos.map(obj -> toDto(obj));
	}

}
