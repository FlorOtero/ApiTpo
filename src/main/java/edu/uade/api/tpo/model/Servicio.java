package edu.uade.api.tpo.model;

import java.util.Collection;

public class Servicio extends Articulo {

    private Collection<String> certificados;
    private TipoContratacion contratacion;

    public Servicio (String id, String nombre, String descripcion, Collection<String> imagenes) {
        super(id, nombre, descripcion, imagenes);
    }

    public Collection<String> getCertificados() {
        return certificados;
    }

    public void setCertificados(Collection<String> certificados) {
        this.certificados = certificados;
    }

    public TipoContratacion getContratacion() {
        return contratacion;
    }

    public void setContratacion(TipoContratacion contratacion) {
        this.contratacion = contratacion;
    }


}
