package edu.uade.api.tpo.model;

public class Servicio {

	private Collection<String> certificados;
	private TipoContratacion contratacion;
	
	public Collection<String> getCertificados() {
		return certificados;
	}
	
	public void setCertificados(Collection<String> certificados) {
		this.certificados = certificados;
	}

	public TipoContratacion getContratacion() {
		return contratacion;
	}

	public void setContratacion(TipoContratacion contratacion) {
		this.contratacion = contratacion;
	}
	
	
}
