package br.com.bepapp.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.bepapp.controller.openapi.ProdutoOpenApi;
import br.com.bepapp.converter.ProdutoConverter;
import br.com.bepapp.domain.dao.DaoProduto;
import br.com.bepapp.domain.entidade.Produto;
import br.com.bepapp.domain.service.ServiceProduto;
import br.com.bepapp.model.dto.ProdutoDTO;
import br.com.bepapp.model.input.ProdutoInput;

@RestController
@RequestMapping("/produtos")
public class ControllerProduto implements ProdutoOpenApi {
	@Autowired
	private ServiceProduto serviceProduto;
	@Autowired
	private ProdutoConverter produtoConverter;
	@Autowired
	private DaoProduto daoProduto;

	@Override
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> buscar(
			@RequestParam(value = "paramentro", required = false, defaultValue = "") String paramentro,
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "size", defaultValue = "4") Integer size, Pageable page) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(produtoConverter.topage(serviceProduto.buscar(paramentro, page)));
	}

	@DeleteMapping("/{idproduto}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Override
	public void remover(@PathVariable Long idproduto) {
		serviceProduto.excluir(idproduto);

	}

	@GetMapping("/{idproduto}")
	@Override
	public ResponseEntity<ProdutoDTO> buscar(@PathVariable Long idproduto) {

		return ResponseEntity.status(HttpStatus.OK).body(produtoConverter.toDto(serviceProduto.buccarporid(idproduto)));
	}

	@PutMapping({ "/{idproduto}" })
	@Override
	public ResponseEntity<ProdutoDTO> Atualizar(@PathVariable Long idproduto,
			@Valid @RequestBody ProdutoInput produtoInput, HttpServletResponse response) {
		produtoInput.setId(idproduto);
		var Produtoatualizado = serviceProduto.salvar(produtoConverter.toEntity(produtoInput));
		return ResponseEntity.status(HttpStatus.OK).body(produtoConverter.toDto(Produtoatualizado));
	}

	@PostMapping
	@Override
	public ResponseEntity<ProdutoDTO> salvar(@Valid @RequestBody ProdutoInput produtoInput,
			HttpServletResponse response) {
		System.out.println(produtoInput);

		var produtosalvo = serviceProduto.salvar(produtoConverter.toEntity(produtoInput));
		return ResponseEntity.status(HttpStatus.OK).body(produtoConverter.toDto(produtosalvo));

	}

	/// @GetMapping
	public ResponseEntity<List<Produto>> lista() {
		return ResponseEntity.status(HttpStatus.OK).body(daoProduto.findAll());
	}

///	@PostMapping

	public ResponseEntity<ProdutoDTO> add(@Valid @RequestBody Produto produtoInput, HttpServletResponse response) {
		System.out.println(produtoInput);
		var produtosalvo = serviceProduto.salvar(produtoInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoConverter.toDto(produtosalvo));
	}

}
