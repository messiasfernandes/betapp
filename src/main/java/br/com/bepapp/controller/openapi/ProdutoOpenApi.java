package br.com.bepapp.controller.openapi;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import br.com.bepapp.domain.entidade.Produto;
import br.com.bepapp.model.dto.ProdutoDTO;
import br.com.bepapp.model.input.ProdutoInput;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Produtos")
public interface ProdutoOpenApi  extends ControllerOpenApi {
	
	@Operation(summary = "Listar  Produtos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Produto  Encotrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) }),
			@ApiResponse(responseCode = "400", description = "Requisão Invaldia", content = @Content),
			@ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content) })
	ResponseEntity<Page<ProdutoDTO>> buscar(String nome, Integer pagina, Integer size,
			Pageable page);

	@Operation(summary = "Excluir um Produto por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Produto excluído"),
			@ApiResponse(responseCode = "404", description = "Produto não encontrada") })

	void remover(@ApiParam(value = "ID de um Produto") Long idproduto);

	ResponseEntity<ProdutoDTO> buscar(@Param(value = "ID de um Produto") Long idproduto);
	@Operation(summary = "Atualizar um Produto ")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Produto Atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "problema com resquisão") })
	ResponseEntity<ProdutoDTO> Atualizar(
			@ApiParam(value = "ID de um produto")
			Long idproduto,
			@Param(value = "corpo") ProdutoInput produtoInput, HttpServletResponse response);

	@Operation(summary = "Salvar um Produto ")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Produto salvo com sucesso"),
			@ApiResponse(responseCode = "400", description = "problema com resquisão") })
	public ResponseEntity<ProdutoDTO> salvar(@Param(value = "corpo") ProdutoInput produtoInput, HttpServletResponse response);

}
