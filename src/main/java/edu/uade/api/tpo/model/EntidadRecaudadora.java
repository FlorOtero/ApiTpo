package edu.uade.api.tpo.model;

import java.io.Serializable;

public abstract class EntidadRecaudadora implements Serializable {

    private String id;

    public abstract void cobrar();

    public void notificar() {

    }

    public void efectuarCobro() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
