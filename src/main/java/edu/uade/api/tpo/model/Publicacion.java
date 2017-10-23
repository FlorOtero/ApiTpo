package edu.uade.api.tpo.model;

import edu.uade.api.tpo.controller.SistemaTransacciones;
import edu.uade.api.tpo.db.Persistible;
import edu.uade.api.tpo.exceptions.BusinessException;

import java.util.Date;
import java.util.List;

public class Publicacion implements Persistible {
	protected String id;
	private Date fechaDesde;
	private Date fechaHasta;
	private Articulo articulo;
	private String usuarioId;
	protected float precio;
	protected Estado estado;
	protected List<MedioPago> mediosPago;

	public Publicacion() {
		super();
	}

	public void ofertar(float monto, Usuario usuario, DatosPago datosPago) throws BusinessException {

		if (estado != Estado.A) {
			throw new BusinessException("La publicacion no está activa!");
		}
		if (!mediosPago.contains(datosPago.getMedioPago())) {
			throw new BusinessException("El medio de pago elegido no está disponible en esta publicación!");
		}
		if (monto != precio) {
			throw new BusinessException("El monto no puede ser diferente al precio de la publicacion");
		}
		this.cerrar(usuario, datosPago);
	}

	public void cerrar(Usuario usuario, DatosPago datosPago) {
		SistemaTransacciones.getInstance().crearTransaccion(usuario, this, datosPago);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public String getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

	public List<MedioPago> getMediosPago() {
		return mediosPago;
	}

	public void setMediosPago(List<MedioPago> mediosPago) {
		this.mediosPago = mediosPago;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
