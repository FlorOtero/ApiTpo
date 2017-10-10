package edu.uade.api.tpo.model;

import edu.uade.api.tpo.db.Persistible;

import java.util.Date;

public class Oferta implements Persistible, Comparable<Oferta> {

	private String id;
	private float monto;
	private Date fecha;
	private Usuario usuario;
	private Subasta subasta;

	public Oferta() {
		this.fecha = new Date();
	}

	public Oferta(float monto, Usuario usuario, Subasta subasta) {
		this();
		this.monto = monto;
		this.usuario = usuario;
		this.subasta = subasta;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Subasta getSubasta() {
		return subasta;
	}

	public void setSubasta(Subasta subasta) {
		this.subasta = subasta;
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

}
