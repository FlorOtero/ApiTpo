package edu.uade.api.tpo.model;

public class EntidadRecaudadoraFactory {

    private static final String BANCO = "Banco";
    private static final String MERCADOPAGO = "MercadoPago";

    public static EntidadRecaudadora getEntidadRecaudadora(String nombre) {
        EntidadRecaudadora entidad = null;
        if (nombre.equals(BANCO)) {
            entidad = new Banco();
        } else if (nombre.equals(MERCADOPAGO)) {
            entidad = new MercadoPago();
        }
        return entidad;
    }

}
