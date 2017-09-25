package edu.uade.api.tpo.model;

import java.util.Date;

public abstract class Transaccion {
	private String id;
	private Usuario contraparte;
	private Publicacion publicacion;
	private char estado;
	private Date fecha;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Usuario getContraparte() {
		return contraparte;
	}
	
	public void setContraparte(Usuario contraparte) {
		this.contraparte = contraparte;
	}
	
	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public abstract void pagar();

}
