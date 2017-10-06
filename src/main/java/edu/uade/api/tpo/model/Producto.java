package edu.uade.api.tpo.model;

import java.util.List;

public class Producto extends Articulo {

    private Garantia garantia;

    public Producto () {

    }

    public Producto(String nombre, String descripcion, List<String> imagenes, Garantia garantia) {
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
