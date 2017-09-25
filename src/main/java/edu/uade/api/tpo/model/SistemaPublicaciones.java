package edu.uade.api.tpo.model;

import java.util.ArrayList;
import java.util.Date;

public class SistemaPublicaciones {
	private static SistemaPublicaciones instance;
	private ArrayList<Publicacion> publicaciones; 
	
	public static SistemaPublicaciones getInstance() {
        if(instance == null){
            instance = new SistemaPublicaciones();
        }
        return instance;
    }
	
	public Publicacion crearPublicacion(Usuario u, Date fechaDesde, Date fechaHasta, float precio, Articulo articulo) {
		Publicacion p = new Publicacion();
		p.setFechaDesde(fechaDesde);
		p.setFechaHasta(fechaHasta);
		p.setPrecio(precio);
		p.setArticulo(articulo);
		this.publicaciones.add(p);
		return p;
	}
	
	public void eliminarPublicacion(Publicacion publicacion) {
		this.publicaciones.remove(publicacion);
	}
	
	public void modificarPublicacion(Publicacion p, Date fechaDesde, Date fechaHasta, float precio, Articulo articulo) {
		p.setFechaDesde(fechaDesde);
		p.setFechaHasta(fechaHasta);
		p.setPrecio(precio);
		p.setArticulo(articulo);
	}
	
	public Subasta crearSubasta(Usuario u, Articulo a, float precioMin, int diasVigencia, float precioInicial) {
		Subasta s = null;
		return s;
	}
	
	public Subasta convertirPublicacionSubasta(Publicacion p, float precioMin, int diasVigencia, float pInicial) {
		Subasta s = null;
		return s;
	}
	
}
