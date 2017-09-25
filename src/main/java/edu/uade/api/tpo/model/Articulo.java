package edu.uade.api.tpo.model;

import java.util.Collection;

public abstract class Articulo {

    private String id;
    private String nombre;
    private String descripcion;
    private Collection<String> imagenes;


    public Articulo(String id, String nombre, String descripcion, Collection<String> imagenes) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
