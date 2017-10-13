package edu.uade.api.tpo.model;

import edu.uade.api.tpo.exceptions.BusinessException;

public class CompraTarjetaCredito extends Transaccion {

    private EntidadRecaudadora entidadRecaudadora;
    private String numeroTarjeta;
    
    public CompraTarjetaCredito() {
        this.entidadRecaudadora = new MercadoPago();
    }

    public CompraTarjetaCredito(Publicacion publicacion, Usuario contraparte, String numeroTarjeta) {
		super(publicacion, contraparte);
        this.numeroTarjeta = numeroTarjeta;
        this.entidadRecaudadora = new MercadoPago();
	}

    public void ejecutar() throws BusinessException{
        this.entidadRecaudadora.informarPago(this);
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public void setEntidadRecaudadora(EntidadRecaudadora entidadRecaudadora) {
        this.entidadRecaudadora = entidadRecaudadora;
    }

    public EntidadRecaudadora getEntidadRecaudadora() {
        return entidadRecaudadora;
    }
}
