package edu.uade.api.tpo.model;

import java.io.Serializable;
import java.util.Date;

public abstract class Transaccion implements Serializable{
	private String id;
	private Publicacion publicacion;
	private char estado;
	private Date fecha;
	private Usuario contraparte;
	private String cuentaCorrienteId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Usuario getContraparte() {
		return contraparte;
	}

	public void setContraparte(Usuario contraparte) {
		this.contraparte = contraparte;
	}

	public String getCuentaCorrienteId() {
		return cuentaCorrienteId;
	}

	public void setCuentaCorrienteId(String cuentaCorrienteId) {
		this.cuentaCorrienteId = cuentaCorrienteId;
	}

	public abstract void pagar();

}
