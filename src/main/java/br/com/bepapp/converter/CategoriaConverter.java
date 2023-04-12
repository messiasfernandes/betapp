package br.com.bepapp.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.bepapp.domain.entidade.Categoria;
import br.com.bepapp.model.dto.CategoriaDTO;
import br.com.bepapp.model.input.CategoriaInput;

@Component
public class CategoriaConverter {

	@Autowired
	private ModelMapper modelMapper;

	public CategoriaDTO toDto(Categoria objeto) {

		return modelMapper.map(objeto, CategoriaDTO.class);
	}
     public Categoria toEntity(CategoriaInput objeto) {
		
		return modelMapper.map(objeto, Categoria.class);
	}

	public Page<CategoriaDTO> topage(Page<Categoria> objetos) {

		return objetos.map(obj -> toDto(obj));
	}
	
}
