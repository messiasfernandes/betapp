package br.com.bepapp.domain.service.exeption;

import org.springframework.dao.EmptyResultDataAccessException;

public class RegistroNaoEncontrado extends EmptyResultDataAccessException {

	private static final long serialVersionUID = 1L;

	public RegistroNaoEncontrado(String mensagem) {
		super(mensagem, 0);

	}
}
