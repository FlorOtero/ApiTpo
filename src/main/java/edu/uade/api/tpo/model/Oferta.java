package edu.uade.api.tpo.model;

import edu.uade.api.tpo.db.Persistible;

import java.util.Date;

public class Oferta implements Persistible {

    private String id;
    private float monto;
    private Date fecha;
    private Usuario usuario;

    public Oferta(float monto, Date fecha, Usuario usuario) {
		super();
		this.monto = monto;
		this.fecha = fecha;
		this.usuario = usuario;
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

}
