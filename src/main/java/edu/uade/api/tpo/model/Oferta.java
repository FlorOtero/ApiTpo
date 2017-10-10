package edu.uade.api.tpo.model;

import edu.uade.api.tpo.db.Persistible;

import java.util.Date;

public class Oferta implements Persistible, Comparable<Oferta> {

	private String id;
	private float monto;
	private Date fecha;
	private String usuarioId;
	private String subastaId;

	public Oferta() {
		this.fecha = new Date();
	}

	public Oferta(float monto, String usuarioId, String subastaId) {
		this();
		this.monto = monto;
		this.usuarioId = usuarioId;
		this.subastaId = subastaId;
	}

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

	public String getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int compareTo(Oferta o) {
		if (this.monto > o.getMonto()) {
			return 1;
		} else if (this.monto < o.getMonto()) {
			return -1;
		}
		return 0;
	}

	public String getSubastaId() {
		return subastaId;
	}

	public void setSubastaId(String subastaId) {
		this.subastaId = subastaId;
	}
}
