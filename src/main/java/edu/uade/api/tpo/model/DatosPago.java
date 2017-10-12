package edu.uade.api.tpo.model;

public class DatosPago {

    private MedioPago medioPago;
    private String numeroCuenta;
    private String numeroTarjeta;

    public MedioPago getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(MedioPago medioPago) {
        this.medioPago = medioPago;
    }

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
