package edu.uade.api.tpo.model;

import edu.uade.api.tpo.db.Persistible;

import java.util.Date;
import java.util.List;

public class Publicacion implements Persistible {
    private String id;
    private Date fechaDesde;
    private Date fechaHasta;
    private float precio;
    private Estado estado;
    private float comision;
    private Articulo articulo;
    private String usuarioId;
    private List<MedioPago> mediosPago;

    public void ofertar(float monto, Usuario usuario) {
    	
    	//creamos la compra comun y corriente
    	

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
