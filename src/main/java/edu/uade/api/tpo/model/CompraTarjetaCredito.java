package edu.uade.api.tpo.model;

import edu.uade.api.tpo.exceptions.BusinessException;

public class CompraTarjetaCredito extends Transaccion {
    private EntidadRecaudadora entidad;
    private String numeroTarjeta;
    
    public CompraTarjetaCredito() {}

    public CompraTarjetaCredito(Publicacion publicacion, Usuario contraparte, String numeroTarjeta) {
		super(publicacion, contraparte);
        this.numeroTarjeta = numeroTarjeta;
	}

	public EntidadRecaudadora getEntidad() {
        return entidad;
    }

    public void setEntidad(EntidadRecaudadora entidad) {
        this.entidad = entidad;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public void pagar() throws BusinessException{

    }
}
