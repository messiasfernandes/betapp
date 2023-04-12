package br.com.bepapp.controller.openapi;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import br.com.bepapp.domain.entidade.Variacoes;
import br.com.bepapp.model.dto.VariacoesDTO;
import br.com.bepapp.model.input.VariacoesInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = " Variações")
public interface VariacoeOpenApi  extends ControllerOpenApi{
	
	@Operation(summary = "Lista de Variações de produtos ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Variação não  Encotrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Variacoes.class)) }),
			@ApiResponse(responseCode = "400", description = "Requisão Invaldia", content = @Content),
			@ApiResponse(responseCode = "404", description = "Variação não encontrado", content = @Content) })
	ResponseEntity<Page<VariacoesDTO>> buscar(String nome, Integer pagina, Integer size,
			Pageable page);
	
	@Operation(summary = "Salvar uma Variação ")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Variacao salvo com sucesso"),
			@ApiResponse(responseCode = "400", description = "problema com resquisão") })
	public ResponseEntity<VariacoesDTO>salvar(@Param(value = "corpo") VariacoesInput variaacaoInput, HttpServletResponse response);
	
	
	public ResponseEntity<Void> remover(@Param(value = "ID de um Variação") Long id);
	
	@Operation(summary = "Atualizar um Variação ")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Variação Atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "problema com resquisão") })
	ResponseEntity<VariacoesDTO> Atualizar(
			@Param(value = "ID de uma Varaição")
			Long id,
	  		@Param(value = "corpo") VariacoesInput variacaoInput, HttpServletResponse response);
	     ResponseEntity<VariacoesDTO> buscar(@Param(value = "ID de uma Variaçãp")
				Long id);

}
