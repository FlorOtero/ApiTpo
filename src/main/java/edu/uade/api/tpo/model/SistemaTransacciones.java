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
		//Transaccion tr = new Transaccion(publicacion, contraparte);
		

	}

}
