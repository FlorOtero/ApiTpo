package edu.uade.api.tpo.model;

import edu.uade.api.tpo.db.Persistible;
import edu.uade.api.tpo.exceptions.BusinessException;

import java.util.Date;
import java.util.List;
import java.util.Observable;

public class Publicacion extends Observable implements Persistible {
    private String id;
    private Date fechaDesde;
    private Date fechaHasta;
    private float precio;
    protected Estado estado;
    private float comision;
    private Articulo articulo;
    private String usuarioId;
    protected List<MedioPago> mediosPago;
   
    public Publicacion() {
		super();
		this.comision = 0.03f;
	}

	public void ofertar(float monto, Usuario usuario, MedioPago mp) throws BusinessException {
    	
	    	if(estado != Estado.A) {
	    		throw new BusinessException("La publicacion no está activa!");
	    	}
	    	if(!mediosPago.contains(mp)) {
	    		throw new BusinessException("El medio de pago elegido no está disponible en esta publicación!");
	    	}
	    	//todo: chequear estado transaccion luego de ofertar
	    	SistemaTransacciones.getInstance().crearTransaccion(usuario, this, mp);
	    	setChanged();
	    	notifyObservers();
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

    public float getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
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
