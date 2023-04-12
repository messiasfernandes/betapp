package br.com.bepapp.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bepapp.controller.openapi.ProdutocomVariacaoOpenapi;
import br.com.bepapp.converter.ProdutoVariacaoConverter;
import br.com.bepapp.domain.service.ServiceProdutoVaricacao;
import br.com.bepapp.model.dto.ProdutocomVariacoesDTO;
import br.com.bepapp.model.input.ProdutoVariacaoInput;

@RestController
@RequestMapping("/produtosvariacoes")
public class ControllerProdutoVariacao implements ProdutocomVariacaoOpenapi {
	@Autowired
	private ServiceProdutoVaricacao serviceProdutovariacao;
	@Autowired
	private ProdutoVariacaoConverter produtoVaricaoConverter;

	@GetMapping
	@Override
	public ResponseEntity<Page<ProdutocomVariacoesDTO>> buscar(
			@RequestParam(value = "paramentro", required = false, defaultValue = "") String paramentro,
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "size", defaultValue = "4") Integer size, Pageable page) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(produtoVaricaoConverter.topage(serviceProdutovariacao.buscar(paramentro, page)));
	}

	@PostMapping
	@Override
	public ResponseEntity<ProdutocomVariacoesDTO> salvar(@Valid @RequestBody ProdutoVariacaoInput produtoInput,
			HttpServletResponse response) {
		var produtosalvo = serviceProdutovariacao.salvar(produtoVaricaoConverter.toEntity(produtoInput));
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoVaricaoConverter.toDto(produtosalvo));
	}

	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		System.err.println(id);
		serviceProdutovariacao.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	@Override
	public ResponseEntity<ProdutocomVariacoesDTO> Atualizar(@PathVariable Long id,
			@Valid @RequestBody ProdutoVariacaoInput produtoInput, HttpServletResponse response) {
		produtoInput.setId(id);
		var produtosalvo = serviceProdutovariacao.salvar(produtoVaricaoConverter.toEntity(produtoInput));
		return ResponseEntity.status(HttpStatus.OK).body(produtoVaricaoConverter.toDto(produtosalvo));
	}
	 @GetMapping("/{id}")
	@Override
	public ResponseEntity<ProdutocomVariacoesDTO> buscar( @PathVariable Long id) {
		// TODO Auto-generated method stub
		 return ResponseEntity.status(HttpStatus.OK)
				.body(produtoVaricaoConverter.toDto(serviceProdutovariacao.buccarporid(id)));
	}
	  
		
}
