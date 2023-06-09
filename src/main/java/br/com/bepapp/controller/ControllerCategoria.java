package br.com.bepapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bepapp.converter.CategoriaConverter;
import br.com.bepapp.domain.dao.DaoCategoria;
import br.com.bepapp.domain.entidade.Categoria;
import br.com.bepapp.model.dto.CategoriaDTO;
import br.com.bepapp.model.input.CategoriaInput;

@CrossOrigin
@RestController
@RequestMapping("/categorias")
public class ControllerCategoria {
	@Autowired
	private DaoCategoria daoCategoria;

	@Autowired
	private CategoriaConverter categoriaConverter;

	@GetMapping
	public ResponseEntity<List<Categoria>> listar() {

		return ResponseEntity.status(HttpStatus.OK).body(daoCategoria.findAll());
	}

	@PostMapping

	public ResponseEntity<CategoriaDTO> adicionar(@Valid @RequestBody CategoriaInput categoria,
			HttpServletResponse response) {

		Categoria categoriasalva = daoCategoria.save(categoriaConverter.toEntity(categoria));
		/// publisher.publishEvent(new RecursoCriadoEvent(this, response,
		/// categoriasalva.getIdcategoria()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaConverter.toDto(categoriasalva));

	}
}
