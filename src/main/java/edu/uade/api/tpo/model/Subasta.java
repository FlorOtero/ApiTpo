package edu.uade.api.tpo.model;

import edu.uade.api.tpo.exceptions.BusinessException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Subasta extends Publicacion {
	private float precioMin;
	private int diasVigencia;
	private float precioInicial;
	private List<Oferta> ofertas;

	public Subasta() {
	}

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

	public void ofertar(float monto, Usuario usuario, MedioPago mp) throws BusinessException {
		if (estado != Estado.A) {
			throw new BusinessException("La publicacion no está activa!");
		}
		if (!mediosPago.contains(mp)) {
			throw new BusinessException("El medio de pago elegido no está disponible en esta publicación!");
		}

		Oferta oferta = new Oferta(monto, usuario.getId(), id);
		ofertas.add(oferta);
		SistemaPublicaciones.getInstance().modificarSubasta(this);
		/*
		// notificamos a todos menos al usuario que hizo la oferta
		Set<String> usuariosNotificados = new HashSet();
		for (Oferta o : ofertas) {
			if (!o.getUsuario().getId().equals(usuario.getId())) {
				usuariosNotificados.add(o.getUsuario().getNombreUsuario());
			}
		}
		SistemaNotificacionSubasta.getInstance().notificarUsuarios(usuariosNotificados, this);
		*/
	}

	private Oferta buscarMayorOferta() {
		Oferta oferta = null;
		if (!this.ofertas.isEmpty()) {
			Collections.sort(this.ofertas);
			oferta = this.ofertas.get(ofertas.size() - 1);
		}
		return oferta;
	}
}
