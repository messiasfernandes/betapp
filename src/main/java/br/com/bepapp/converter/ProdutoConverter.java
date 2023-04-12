package br.com.bepapp.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.bepapp.domain.entidade.Produto;
import br.com.bepapp.model.dto.ProdutoDTO;
import br.com.bepapp.model.input.ProdutoInput;

@Component
public class ProdutoConverter {
	@Autowired
	private ModelMapper modelMapper;

	public ProdutoDTO toDto(Produto objeto) {

		return modelMapper.map(objeto, ProdutoDTO.class);
	}
     public Produto toEntity(ProdutoInput objeto) {
		
		return modelMapper.map(objeto, Produto.class);
	}

	public Page<ProdutoDTO> topage(Page<Produto> objetos) {

		return objetos.map(obj -> toDto(obj));
	}
}
