package edu.uade.api.tpo.exceptions;

import edu.uade.api.tpo.model.Usuario;

public class ExpiredPasswordException extends Exception {

	private Usuario usuario;

	public ExpiredPasswordException(String message, Usuario usuario) {
		super(message);
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

}
