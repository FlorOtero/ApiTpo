package edu.uade.api.tpo.model;

import java.util.List;

public class ArticuloFactory {
	
	public static Articulo crearProducto(String nombre, String descripcion, List<String> imagenes, Garantia garantia) {
		Articulo pro = new Producto(nombre, descripcion, imagenes, garantia);
		return pro;
	}
	public static Articulo crearServicio(String nombre, String descripcion, List<String> imagenes, List<String> certificados, TipoContratacion contratacion) {
		Articulo ser = new Servicio(nombre, descripcion, imagenes, certificados, contratacion);
		return ser;
	}
}
