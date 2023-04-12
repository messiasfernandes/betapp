package br.com.bepapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bepapp.domain.dao.DaoVariante;
import br.com.bepapp.domain.entidade.Variants;

@RestController
@RequestMapping("/variantes")
public class ControllerVariantes {
	@Autowired
	private DaoVariante daoVariante;
 
	@GetMapping
	 public ResponseEntity<List<Variants>>listar (){
		 return ResponseEntity.status(HttpStatus.OK).body(daoVariante.findAll());
		
	}
}
