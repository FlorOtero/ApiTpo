package edu.uade.api.tpo.model;

import java.io.Serializable;
import java.util.Date;

public class Oferta implements Serializable {

    public float monto;
    public Date fecha;
    private Usuario usuario;

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
}
