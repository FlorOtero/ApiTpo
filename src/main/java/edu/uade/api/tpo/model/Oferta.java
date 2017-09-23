package edu.uade.api.tpo.model;

import java.util.Date;

public class Oferta {
	float monto;
	Date fecha;
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
