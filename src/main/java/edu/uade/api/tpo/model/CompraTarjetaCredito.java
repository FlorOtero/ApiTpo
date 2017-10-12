package edu.uade.api.tpo.model;

import edu.uade.api.tpo.exceptions.BusinessException;

public class CompraTarjetaCredito extends Transaccion {

    private EntidadRecaudadora entidadRecaudadora;
    private String numeroTarjeta;
    
    public CompraTarjetaCredito() {}

    public CompraTarjetaCredito(Publicacion publicacion, Usuario contraparte, String numeroTarjeta) {
		super(publicacion, contraparte);
        this.numeroTarjeta = numeroTarjeta;
        this.entidadRecaudadora = new Banco();
	}

    public void pagar() throws BusinessException{
        this.entidadRecaudadora.informarPago(this);
    }
}
