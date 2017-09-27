package edu.uade.api.tpo.model;

import java.util.Collection;

public class Producto extends Articulo {

    private Garantia garantia;

    public Producto(String nombre, String descripcion, Collection<String> imagenes, Garantia garantia) {
        super(nombre, descripcion, imagenes);
        this.garantia = garantia;
    }

    public Garantia getGarantia() {
        return garantia;
    }

    public void setGarantia(Garantia garantia) {
        this.garantia = garantia;
    }


}
