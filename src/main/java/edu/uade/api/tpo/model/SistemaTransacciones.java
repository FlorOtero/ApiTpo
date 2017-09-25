package edu.uade.api.tpo.model;

import java.util.ArrayList;

public class SistemaTransacciones {
	ArrayList<Transaccion>transacciones;
	private static SistemaTransacciones instance;

	public static SistemaTransacciones getInstance() {
		if(instance == null){
			instance = new SistemaTransacciones();
		}
		return instance;
	}

	public void confirmarCompra(Transaccion c){
		transacciones.add(c);
	}

	public Transaccion crearCompra(Usuario contraparteCompra, Publicacion publicacionCompra){
		Transaccion compra = null;  // TODO: fix with constructor
		compra.setContraparte(contraparteCompra);
		compra.setPublicacion(publicacionCompra);
		//	transacciones.add(compra);
		return compra;
	}

	public Transaccion crearVenta(Usuario contraparteVenta, Publicacion publicacionVenta){
		Transaccion venta = null; // TODO: fix with constructor
		venta.setContraparte(contraparteVenta);
		venta.setPublicacion(publicacionVenta);
		transacciones.add(venta);
		return venta;
	}

}
