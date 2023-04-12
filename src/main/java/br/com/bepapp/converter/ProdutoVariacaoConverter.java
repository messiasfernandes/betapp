package br.com.bepapp.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.bepapp.domain.entidade.ProdutoVariacoes;
import br.com.bepapp.model.dto.ProdutocomVariacoesDTO;
import br.com.bepapp.model.input.ProdutoVariacaoInput;

@Component
public class ProdutoVariacaoConverter {
	@Autowired
	private ModelMapper modelMapper;

	public ProdutocomVariacoesDTO toDto(ProdutoVariacoes objeto) {

		return modelMapper.map(objeto, ProdutocomVariacoesDTO.class);
	}

	public ProdutoVariacoes toEntity(ProdutoVariacaoInput objeto) {

		return modelMapper.map(objeto, ProdutoVariacoes.class);
	}

	public Page<ProdutocomVariacoesDTO> topage(Page<ProdutoVariacoes> objetos) {

		return objetos.map(obj -> toDto(obj));
	}
	
	
	

}
