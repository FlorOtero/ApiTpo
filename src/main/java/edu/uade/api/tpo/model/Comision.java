package edu.uade.api.tpo.model;

import java.util.Date;

public class Comision {
	
	float importe;
	Date fecha;

	public Comision(float importe) {
		super();
		this.importe = importe;
		this.fecha = new Date();
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
