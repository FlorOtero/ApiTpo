package edu.uade.api.tpo.model;

import edu.uade.api.tpo.db.Persistible;

public class Comision implements Persistible {

	private String id;
	private float importe;

	public Comision() {

	}

	public Comision(float importe) {
		this.importe = importe;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}
}
