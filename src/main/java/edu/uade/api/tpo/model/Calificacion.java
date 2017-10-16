package edu.uade.api.tpo.model;

import edu.uade.api.tpo.db.Persistible;

import java.util.Date;

public class Calificacion implements Persistible {

    private String id;
    private int calificacion;
    private String observaciones;
    private Date fecha;
    private Estado estado;

    public Calificacion() {

    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
