package edu.uade.api.tpo.model;

import java.io.Serializable;
import java.util.Collection;

public class Reputacion implements Serializable {

    private Collection<Calificacion> calificaciones;

    public Collection<Calificacion> getCalificaciones() {
        return this.calificaciones;
    }

    public void setCalificaciones(Collection<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public int calcularReputacion() {
        return 0;
    }

}
