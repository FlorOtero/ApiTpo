package edu.uade.api.tpo.model;

import edu.uade.api.tpo.exceptions.BusinessException;
import edu.uade.api.tpo.exceptions.InvalidPasswordException;

public class CompraTransferenciaBancaria extends Transaccion {

	private EntidadRecaudadora entidadRecaudadora;
	private String numeroCta;
	
	public CompraTransferenciaBancaria() {
		this.entidadRecaudadora = new Banco();
	}

	public CompraTransferenciaBancaria(Publicacion publicacion, Usuario contraparte, String numeroCta) {
		super(publicacion, contraparte);
		this.numeroCta = numeroCta;
		this.entidadRecaudadora = new Banco();
	}

	@Override
	public void ejecutar() throws BusinessException, InvalidPasswordException {
		entidadRecaudadora.informarPago(this);
	}

	public String getNumeroCta() {
		return numeroCta;
	}

	public EntidadRecaudadora getEntidadRecaudadora() {
		return entidadRecaudadora;
	}

	public void setEntidadRecaudadora(EntidadRecaudadora entidadRecaudadora) {
		this.entidadRecaudadora = entidadRecaudadora;
	}

	public void setNumeroCta(String numeroCta) {
		this.numeroCta = numeroCta;
	}
}
