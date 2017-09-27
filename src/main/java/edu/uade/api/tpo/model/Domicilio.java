package edu.uade.api.tpo.model;

import java.io.Serializable;

public class Domicilio implements Serializable {

    private String id;
    private String linea1;
    private String linea2;
    private String cp;
    private String ciudad;
    private String provincia;

    public Domicilio(String linea1, String linea2, String cp, String ciudad, String provincia) {
        super();
        this.linea1 = linea1;
        this.linea2 = linea2;
        this.cp = cp;
        this.ciudad = ciudad;
        this.provincia = provincia;
    }

    public String getlinea1() {
        return linea1;
    }

    public void setlinea1(String linea1) {
        this.linea1 = linea1;
    }

    public String getlinea2() {
        return linea2;
    }

    public void setlinea2(String linea2) {
        this.linea2 = linea2;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
