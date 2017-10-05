package edu.uade.api.tpo.model;

import edu.uade.api.tpo.db.Persistible;

public class Garantia implements Persistible {

    private String id;
    private int cantidad;
    private TipoPeriodo tipo;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public TipoPeriodo getTipo() {
        return tipo;
    }

    public void setTipo(TipoPeriodo tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
