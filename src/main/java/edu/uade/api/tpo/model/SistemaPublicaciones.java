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
	
	public Publicacion crearPublicacion(Usuario u, Date fechaDesde, Date fechaHasta, float precio, String articulo) {
		Publicacion p = new Publicacion();
		p.setFechaDesde(fechaDesde);
		p.setFechaHasta(fechaHasta);
		p.setPrecio(precio);
		this.publicaciones.add(p);
		// TODO: update setArticulo() when available
		return p;
	}
	
	public void eliminarPublicacion(Publicacion publicacion) {
		this.publicaciones.remove(publicacion);
	}
	
	public void modificarPublicacion(Publicacion p, Date fechaDesde, Date fechaHasta, float precio, String articulo) {
		p.setFechaDesde(fechaDesde);
		p.setFechaHasta(fechaHasta);
		p.setPrecio(precio);
		// TODO: update setArticulo() when available
	}
	
	public Subasta crearSubasta(Usuario u, String a, float precioMin, int diasVigencia, float precioInicial) {
		// TODO: update Articulo when available
		Subasta s = null;
		return s;
	}
	
	public Subasta convertirPublicacionSubasta(Publicacion p, float precioMin, int diasVigencia, float pInicial) {
		// TODO: finish method
		Subasta s = null;
		return s;
	}
	
}
