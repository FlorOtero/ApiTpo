package edu.uade.api.tpo.model;

import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Servicio extends Articulo {

    private List<String> certificados;
    private TipoContratacion contratacion;

    public Servicio() {

    }

    public Servicio(String nombre, String descripcion, List<String> imagenes, List<String> certificados, TipoContratacion contratacion) {
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

    public String toCertificadosTokenized() {
        String result = "";
        for (String certificado : this.certificados) {
            result = result.concat(certificado).concat(",");
        }
        return result.substring(0, result.lastIndexOf(","));
    }

    public void fromCertificadosTokenized(String certificados) {
        if (!Strings.isNullOrEmpty(certificados)) {
            this.certificados = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(certificados, ",");
            while (st.hasMoreElements()) {
                this.certificados.add(st.nextToken());
            }
        }
    }

}
