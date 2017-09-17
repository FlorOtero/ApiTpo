package edu.uade.api.tpo.model;

import java.io.Serializable;
import java.util.Collection;

public abstract class Articulo implements Serializable {

    private String nombre;
    private String descripcion;
    private Collection<String> imagenes;

}
