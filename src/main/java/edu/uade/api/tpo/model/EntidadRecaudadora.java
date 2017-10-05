package edu.uade.api.tpo.model;

import edu.uade.api.tpo.db.Persistible;

public abstract class EntidadRecaudadora implements Persistible {

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
