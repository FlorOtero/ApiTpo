package edu.uade.api.tpo.model;

public class DatosPago {

    private String numeroCuenta;
    private String numeroTarjeta;

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }
}
