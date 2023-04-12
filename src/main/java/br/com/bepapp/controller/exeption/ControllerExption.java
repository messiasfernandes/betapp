package br.com.bepapp.controller.exeption;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.bepapp.domain.service.exeption.RegistroNaoEncontrado;

@ControllerAdvice
public class ControllerExption extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		var campos = new ArrayList<Problema.Campo>();

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

			campos.add(new Problema.Campo(nome, mensagem));
		}

		var problema = Problema.builder().
				status(status.value()).
				titulo("Um ou mais campos estão inválidos. " + "Faça o preenchimento correto e tente novamente").
				status(status.value()).
				dataHora(OffsetDateTime.now()).campos(campos).build();
                         
		return handleExceptionInternal(ex, problema, headers, status, request);

	}
	@ExceptionHandler(RegistroNaoEncontrado.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(RegistroNaoEncontrado ex, WebRequest request) {
		var status = HttpStatus.NOT_FOUND;


		var problema = Problema.builder().
				status(status.value()).
				titulo(ex.getMessage()).
				status(status.value()).
				dataHora(OffsetDateTime.now()).build();

		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {
		var status = HttpStatus.BAD_REQUEST;

		var problema = Problema.builder().
				status(status.value()).
				titulo("Registro não encontrado").
				status(status.value()).
				dataHora(OffsetDateTime.now()).build();

		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
}
