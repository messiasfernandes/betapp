package br.com.bepapp.controller.openapi;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import br.com.bepapp.domain.entidade.ProdutoVariacoes;
import br.com.bepapp.model.dto.ProdutocomVariacoesDTO;
import br.com.bepapp.model.input.ProdutoVariacaoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = " Produtos com Variações")
public interface ProdutocomVariacaoOpenapi   extends ControllerOpenApi{
	
	@Operation(summary = "Lista de Produtos com ou sem variação ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Produto  Encotrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoVariacoes.class)) }),
			@ApiResponse(responseCode = "400", description = "Requisão Invaldia", content = @Content),
			@ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content) })
	ResponseEntity<Page<ProdutocomVariacoesDTO>> buscar(String nome, Integer pagina, Integer size,
			Pageable page);
	
	@Operation(summary = "Salvar um Produto com Variação ")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Produto salvo com sucesso"),
			@ApiResponse(responseCode = "400", description = "problema com resquisão") })
	public ResponseEntity<ProdutocomVariacoesDTO>salvar(@Param(value = "corpo") ProdutoVariacaoInput produtoInput, HttpServletResponse response);
      
	@Operation(summary = "Excluid um Produto com vaaricao  por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Produto excluído"),
			@ApiResponse(responseCode = "404", description = "Produto não encontrada") })

	public ResponseEntity<Void> remover(@Param(value = "ID de um Produto") Long id);
	@Operation(summary = "Atualizar um Produto com variarção ")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Produto Atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "problema com resquisão") })
	ResponseEntity<ProdutocomVariacoesDTO> Atualizar(
			@Param(value = "ID de um Produto com varição")
			Long id,
	  		@Param(value = "corpo") ProdutoVariacaoInput produtoInput, HttpServletResponse response);
	     ResponseEntity<ProdutocomVariacoesDTO> buscar(@Param(value = "ID de uma Produto com variaçãp")
				Long id);
	
	
}
