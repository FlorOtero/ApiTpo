package edu.uade.api.tpo.model;

import java.util.ArrayList;
import java.util.List;
import edu.uade.api.tpo.controller.SistemaNotificacionSubasta;
import edu.uade.api.tpo.controller.SistemaPublicaciones;
import edu.uade.api.tpo.exceptions.BusinessException;
import java.util.stream.Collectors;

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
		if(monto <= getPrecioActual()) {
			throw new BusinessException("El monto ofertado es menor que el precio actual");
		}

		Oferta oferta = new Oferta(monto, usuario.getId(), id);
		ofertas.add(oferta);
		SistemaPublicaciones.getInstance().modificarSubasta(this);

		// notificamos a todos menos al usuario que hizo la oferta
		ofertas.stream().filter(o -> !o.getUsuarioId().equals(usuario.getId())).map(o -> o.getUsuarioId()).collect(Collectors.toSet()).forEach(usuarioId -> {
			SistemaNotificacionSubasta.getInstance().notificarUsuarioSubasta(usuarioId, this);
		});
	}

	private float getPrecioActual() {
		if(this.ofertas == null || this.ofertas.isEmpty()) {
			return this.getPrecioInicial();
		} else {
			return this.ofertas.get(0).getMonto();
		}
	}
}
