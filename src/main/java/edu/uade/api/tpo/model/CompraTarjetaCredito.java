package edu.uade.api.tpo.model;

public class CompraTarjetaCredito extends Transaccion {
    private EntidadRecaudadora entidad;
    private String numeroTarjeta;
    
    public CompraTarjetaCredito() {}

    public CompraTarjetaCredito(Publicacion publicacion, Usuario contraparte, String cuentaCorrienteId) {
		super(publicacion, contraparte, cuentaCorrienteId);
		// TODO Auto-generated constructor stub
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

    public void pagar() {

    }
}
