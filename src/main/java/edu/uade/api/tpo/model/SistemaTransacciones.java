package edu.uade.api.tpo.model;

import java.util.List;
import java.util.ArrayList;

public class SistemaTransacciones {
	List<Transaccion>transacciones;
	private static SistemaTransacciones instance = null;
	
	private SistemaTransacciones() {}

	public static SistemaTransacciones getInstance() {
		if(instance == null){
			instance = new SistemaTransacciones();
		}
		return instance;
	}

	public void confirmarCompra(Transaccion c){
		transacciones.add(c);
	}

	public void crearTransaccion(Usuario contraparte, Publicacion publicacion, MedioPago medioDePago){
		Transaccion tr = null;
		//todo: cambiar estado publicacion y transaccion despues de recibir la respuesta
		switch (medioDePago) {
		case EFECTIVO:
			publicacion.setEstado(Estado.I);
			tr = new CompraEfectivo(publicacion, contraparte);
			break;
		case TRANSFERENCIA_BANCARIA:
			publicacion.setEstado(Estado.P);
			tr = new CompraTransferenciaBancaria(publicacion, contraparte);
			//	SistemaNotificacionCobro.getInstance()...
			break;
		case TARJETA_CREDITO:
			publicacion.setEstado(Estado.P);
			tr = new CompraTarjetaCredito(publicacion, contraparte);
			//	SistemaNotificacionCobro.getInstance()...
			break;
		}
		
		transacciones.add(tr);
		SistemaPublicaciones.getInstance().modificarPublicacion(publicacion);
	}

}
