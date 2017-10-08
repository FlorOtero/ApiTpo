package edu.uade.api.tpo.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class Subasta extends Publicacion {
	private float precioMin;
	private int diasVigencia;
	private float precioInicial;
	private List<Oferta> ofertas;

	public Subasta(float precioMin, int diasVigencia, float precioInicial) {
		super();
		this.precioMin = precioMin;
		this.diasVigencia = diasVigencia;
		this.precioInicial = precioInicial;
		this.ofertas = new ArrayList<>();
	}

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

	public void ofertar(float monto, Usuario usuario) {
		Date now = new Date();
		Oferta oferta = new Oferta(monto, now, usuario);
		//creamos la oferta
		ofertas.add(oferta);
		//notificamos a todos menos al usuario que hizo la oferta
		Set<String> usuariosNotificados = new HashSet();
		for(Oferta o : ofertas) {
			if(!o.getUsuario().getId().equals(usuario.getId())) {
				usuariosNotificados.add(o.getUsuario().getNombreUsuario());
			}
		}
		SistemaNotificacionSubasta.getInstance().notificarUsuarios(usuariosNotificados, this);
	}

}
