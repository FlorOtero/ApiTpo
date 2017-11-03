package edu.uade.api.tpo.model;

import edu.uade.api.tpo.controller.SistemaTransacciones;
import edu.uade.api.tpo.exceptions.BusinessException;
import edu.uade.api.tpo.exceptions.InvalidPasswordException;

public class CompraEfectivo extends Transaccion {
	
	public CompraEfectivo() {}
	
	public CompraEfectivo(Publicacion publicacion, Usuario contraparte, Usuario usuarioPublicacion) {
		super(publicacion, contraparte, usuarioPublicacion);
		this.setEstado(EstadoTransaccion.A);
	}

	public void ejecutar() throws BusinessException, InvalidPasswordException {
		SistemaTransacciones.getInstance().aprobarTransaccion(this);
		
	}
}
