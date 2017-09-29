package edu.uade.api.tpo.model;

import com.google.common.base.Strings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public abstract class Articulo implements Serializable {

    private String id;
    private String nombre;
    private String descripcion;
    private List<String> imagenes;

    public Articulo() {

    }

    public Articulo(String nombre, String descripcion, List<String> imagenes) {
        super();
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

    public List<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }

    public String toImagesTokenized() {
        String result = "";
        for (String imagen : this.imagenes) {
            result = result.concat(imagen).concat(",");
        }
        return result.substring(0, result.lastIndexOf(","));
    }

    public void fromImagesTokenized(String images) {
        if(!Strings.isNullOrEmpty(images)) {
            this.imagenes = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(images, ",");
            while(st.hasMoreElements()) {
                this.imagenes.add(st.nextToken());
            }
        }
    }
}
