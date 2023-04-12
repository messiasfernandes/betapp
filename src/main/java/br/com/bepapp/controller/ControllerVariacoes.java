package br.com.bepapp.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bepapp.controller.openapi.VariacoeOpenApi;
import br.com.bepapp.converter.VariacaoConverter;
import br.com.bepapp.domain.service.ServiceVariacoes;
import br.com.bepapp.model.dto.VariacoesDTO;
import br.com.bepapp.model.input.VariacoesInput;

@RestController
@RequestMapping("/variacoes")
public class ControllerVariacoes implements VariacoeOpenApi {
	@Autowired
	private ServiceVariacoes serviceVariacoes;
	@Autowired
	private VariacaoConverter variacaoConverter;
	@GetMapping
	@Override
	public ResponseEntity<Page<VariacoesDTO>> buscar(@RequestParam(value = "paramentro", required = false, defaultValue = "") String paramentro,
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "size", defaultValue = "4") Integer size,Pageable page) {
		
		return ResponseEntity.status(HttpStatus.OK).body(variacaoConverter.topage(serviceVariacoes.buscar(paramentro, page)));
	}
    @PostMapping
	@Override
	public ResponseEntity<VariacoesDTO> salvar( @Valid  @RequestBody VariacoesInput variaacaoInput, HttpServletResponse response) {
		var variacaosalva = serviceVariacoes.salvar(variacaoConverter.toEntity(variaacaoInput));
		return ResponseEntity.status(HttpStatus.CREATED).body(variacaoConverter.toDto(variacaosalva));
	}
	@Override
	public ResponseEntity<Void> remover(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@PutMapping("/{id}")
	@Override
	public ResponseEntity<VariacoesDTO> Atualizar (@PathVariable Long id, @Valid @RequestBody  VariacoesInput variacaoInput, HttpServletResponse response) {
		variacaoInput.setId(id);
		var variacaoEditada= serviceVariacoes.salvar(variacaoConverter.toEntity(variacaoInput));
		return ResponseEntity.status(HttpStatus.OK).body(variacaoConverter.toDto(variacaoEditada));
	}
	@Override
	public ResponseEntity<VariacoesDTO> buscar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
