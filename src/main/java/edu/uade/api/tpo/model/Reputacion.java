package edu.uade.api.tpo.model;

import java.util.ArrayList;
import java.util.List;

public class Reputacion {

    private List<Calificacion> calificaciones;

    public Reputacion() {
        super();
        calificaciones = new ArrayList<Calificacion>();
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificacion(Calificacion c) {
        calificaciones.add(c);
    }

    public Calificacion getCalificacionByIdTransaccion(String idTransaccion) {
        Calificacion c = null;

        for (int i = 0; i < calificaciones.size(); i++) {
            if (calificaciones.get(i).getTransaccion().getId().equals(idTransaccion)) {
                c = calificaciones.get(i);
            }
        }

        return c;
    }

    public int calcularReputacion() {

        int rep = 0;

        for (int i = 0; i < calificaciones.size(); i++) {
            rep += calificaciones.get(i).getCalificacion();
        }

        return (rep / calificaciones.size());
    }

}
