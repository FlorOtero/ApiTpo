package edu.uade.api.tpo.model;

import edu.uade.api.tpo.db.Persistible;
import edu.uade.api.tpo.exceptions.BusinessException;
import edu.uade.api.tpo.exceptions.InvalidPasswordException;

import java.util.Date;

public abstract class Transaccion implements Persistible {
	private String id;
	private Publicacion publicacion;
	private EstadoTransaccion estado;
	private Date fecha;
	private String contraparteId;
	private String cuentaCorrienteId;
	private Comision comision;
	private Calificacion calificacion;
	private float monto;
	
	public Transaccion() {}
	
	public Transaccion(Publicacion publicacion, Usuario contraparte, Usuario usuarioPublicacion) {
		super();
		this.publicacion = publicacion;
		this.estado = EstadoTransaccion.P;
		this.fecha = new Date();
		this.contraparteId = contraparte.getId();
		this.cuentaCorrienteId = usuarioPublicacion.getCuentaCorriente().getId();
		this.monto = publicacion.getPrecio();
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

	public String getContraparteId() {
		return contraparteId;
	}

	public void setContraparteId(String contraparteId) {
		this.contraparteId = contraparteId;
	}

	public String getCuentaCorrienteId() {
		return cuentaCorrienteId;
	}

	public void setCuentaCorrienteId(String cuentaCorrienteId) {
		this.cuentaCorrienteId = cuentaCorrienteId;
	}

	public abstract void ejecutar() throws BusinessException, InvalidPasswordException;

	public Comision getComision() {
		return comision;
	}

	public void setComision(Comision comision) {
		this.comision = comision;
	}

	public Calificacion getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Calificacion calificacion) {
		this.calificacion = calificacion;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}
}
