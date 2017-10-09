package edu.uade.api.tpo.model;

import edu.uade.api.tpo.db.Persistible;

import java.util.Date;

public abstract class Transaccion implements Persistible {
	private String id;
	private Publicacion publicacion;
	private EstadoTransaccion estado;
	private Date fecha;
	private Usuario contraparte;
	private String cuentaCorrienteId;
	
	public Transaccion() {}
	
	public Transaccion(Publicacion publicacion, Usuario contraparte, String cuentaCorrienteId) {
		super();
		this.publicacion = publicacion;
		this.estado = EstadoTransaccion.P;
		this.fecha = new Date();
		this.contraparte = contraparte;
		this.cuentaCorrienteId = cuentaCorrienteId;
	}

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

    public EstadoTransaccion getEstado() {
        return estado;
    }

    public void setEstado(EstadoTransaccion estado) {
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
