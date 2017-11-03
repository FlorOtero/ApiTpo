package edu.uade.api.tpo.model;

import java.util.Date;

public class ItemCtaCte {
	
	//{"Fecha", "Título", "Estado", "Tipo", "Monto", "Calificación"};
	private String idOperacion;
	private float monto;
	private boolean comision;
	private String estado;
	private String tipo;
	private String titulo;
	private Date fecha;
	private boolean calificada;
	
	public ItemCtaCte(String idOperacion) {
		super();
		this.idOperacion = idOperacion;
	}
	public String getIdOperacion() {
		return idOperacion;
	}
	public void setIdOperacion(String idOperacion) {
		this.idOperacion = idOperacion;
	}
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
	public boolean isComision() {
		return comision;
	}
	public void setComision(boolean comision) {
		this.comision = comision;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public boolean isCalificada() {
		return calificada;
	}
	public void setCalificada(boolean calificada) {
		this.calificada = calificada;
	}

}
