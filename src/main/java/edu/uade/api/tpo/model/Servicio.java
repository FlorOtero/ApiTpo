package edu.uade.api.tpo.model;

import java.util.Collection;
import java.util.List;

public class Servicio extends Articulo {

    private List<String> certificados;
    private TipoContratacion contratacion;

    public Servicio(String nombre, String descripcion, Collection<String> imagenes, List<String> certificados, TipoContratacion contratacion) {
        super(nombre, descripcion, imagenes);
        this.certificados = certificados;
        this.contratacion = contratacion;
    }

    public List<String> getCertificados() {
        return certificados;
    }

    public void setCertificados(List<String> certificados) {
        this.certificados = certificados;
    }

    public TipoContratacion getContratacion() {
        return contratacion;
    }

    public void setContratacion(TipoContratacion contratacion) {
        this.contratacion = contratacion;
    }

}
