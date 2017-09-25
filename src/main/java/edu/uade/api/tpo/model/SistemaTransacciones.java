package edu.uade.api.tpo.model;

import java.util.ArrayList;

public class SistemaTransacciones {

	public Transaccion SistemaTransacciones() {
		//private static SistemaTransacciones instance;
		ArrayList<Transaccion>transacciones; 
		
		public confirmarCompra(Transaccion c){
			transacciones.add(c);
		}
		
		public Transaccion crearCompra(Usuario contraparteCompra, Publicacion publicacionCompra){
			Transaccion compra = null;
			compra.setContraparte(contraparteCompra);
			compra.setPublicacion(publicacionCompra);
		//	transacciones.add(compra);
			return compra;
		}
		
		public Transaccion crearVenta(Usuario contraparteVenta, Publicacion publicacionVenta){
			Transaccion venta = null;
			venta.setContraparte(contraparteVenta);
			venta.setPublicacion(publicacionVenta);
			transacciones.add(venta);
			return venta;
		}
		
		
	}

}
