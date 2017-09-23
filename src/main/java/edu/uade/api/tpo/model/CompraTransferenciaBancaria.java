package edu.uade.api.tpo.model;

public class CompraTransferenciaBancaria extends Transaccion {
	EntidadRecaudadora entidad;
	String numeroCta;
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
	
	public void pagar(){
		
	}
}
