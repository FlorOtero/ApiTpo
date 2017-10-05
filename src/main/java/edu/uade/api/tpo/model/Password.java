package edu.uade.api.tpo.model;

import edu.uade.api.tpo.db.Persistible;

import java.util.Date;

public class Password implements Persistible {

    private String id;
    private String valor;
    private Date fechaModificacion;

    public Password() {

    }

    public Password(String valor, Date fechaModificacion) {
        super();
        this.valor = valor;
        this.fechaModificacion = fechaModificacion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
