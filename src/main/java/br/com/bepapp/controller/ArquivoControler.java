package br.com.bepapp.controller;

import java.io.File;
import java.io.IOException;

import java.util.List;

import javax.activation.FileTypeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.bepapp.controller.openapi.ArquivoOpenApi;
import br.com.bepapp.domain.service.ServiceStorage;
import br.com.bepapp.model.dto.ArquivoDto;

@RestController
@RequestMapping("/arquivos/fotos")
public class ArquivoControler implements ArquivoOpenApi {
	@Autowired
	private ServiceStorage serviceStorage;

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Override
	public ResponseEntity<List<ArquivoDto>> upload(@RequestParam List<MultipartFile> arquivo) {

		return ResponseEntity.status(HttpStatus.OK).body(serviceStorage.salvar(arquivo));
	}

	@GetMapping("/{arquivo}")
	@Override
	public ResponseEntity<byte[]> getImage(@PathVariable String arquivo) throws IOException {

		File img = serviceStorage.buscarfoto(arquivo);
		System.out.println(img);
		return ResponseEntity.ok()
				.contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img)))
				.body(serviceStorage.carregarFoto(img));
	}

	@DeleteMapping("/{nomeArquivo}")
	public ResponseEntity<Void> deleteArquivo(@PathVariable String nomeArquivo) {

		serviceStorage.delete(nomeArquivo);

		return ResponseEntity.noContent().build();

	}
}
