package edu.uade.api.tpo.model;

import edu.uade.api.tpo.exceptions.BusinessException;

public class CompraTransferenciaBancaria extends Transaccion {
	private EntidadRecaudadora entidad;
	private String numeroCta;
	
	public CompraTransferenciaBancaria() {}

	public CompraTransferenciaBancaria(Publicacion publicacion, Usuario contraparte) {
		super(publicacion, contraparte);
		// TODO Auto-generated constructor stub
	}

	public EntidadRecaudadora getEntidad() {
		return entidad;
	}

	public void setEntidad(EntidadRecaudadora entidad) {
		this.entidad = entidad;
	}

	public String getNumeroCta() {
		return numeroCta;
	}

	public void setNumeroCta(String numeroCta) {
		this.numeroCta = numeroCta;
	}

	public void pagar() throws BusinessException{

	}
}
