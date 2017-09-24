package edu.uade.api.tpo.model;

import java.util.List;

public class Subasta extends Publicacion {
	private float precioMin;
	private int diasVigencia;
	private float precioInicial;
	private List<Oferta> ofertas;
	
	public float getPrecioMin() {
		return precioMin;
	}
	public void setPrecioMin(float precioMin) {
		this.precioMin = precioMin;
	}
	public int getDiasVigencia() {
		return diasVigencia;
	}
	public void setDiasVigencia(int diasVigencia) {
		this.diasVigencia = diasVigencia;
	}
	public float getPrecioInicial() {
		return precioInicial;
	}
	public void setPrecioInicial(float precioInicial) {
		this.precioInicial = precioInicial;
	}
	public List<Oferta> getOfertas() {
		return ofertas;
	}
	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}
	
	
}
