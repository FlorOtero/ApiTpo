package edu.uade.api.tpo.model;

public class CompraEfectivo extends Transaccion {
	
	public CompraEfectivo() {}
	
	public CompraEfectivo(Publicacion publicacion, Usuario contraparte, String cuentaCorrienteId) {
		super(publicacion, contraparte, cuentaCorrienteId);
		// TODO Auto-generated constructor stub
	}

	public void pagar() {

	}
}
