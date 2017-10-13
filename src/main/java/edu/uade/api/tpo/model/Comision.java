package edu.uade.api.tpo.model;

import java.util.Date;

public class Comision {
	
	float importe;
	String idTransaccion;

	public Comision(float importe) {
		super();
		this.importe = importe;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public String getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}


}
