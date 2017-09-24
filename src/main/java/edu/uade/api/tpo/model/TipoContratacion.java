package edu.uade.api.tpo.model;

public enum TipoContratacion {

	contratacion1("ABONO"),
	contratacion2("UNICA");

	private String tipoContratacion;
	
	TipoContratacion(final String tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

	public String getTipoContratacion() {
		return tipoContratacion;
	}

	public void setTipoContratacion(String tipoContratacion) {
		this.tipoContratacion = tipoContratacion;
	}
	

	
}
