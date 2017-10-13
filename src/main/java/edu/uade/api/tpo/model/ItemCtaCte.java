package edu.uade.api.tpo.model;

public class ItemCtaCte {
	
	private String idOperacion;
	private float monto;
	private boolean comision;
	private char estado;
	private String tipo;
	
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
	public char getEstado() {
		return estado;
	}
	public void setEstado(char estado) {
		this.estado = estado;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	

}
