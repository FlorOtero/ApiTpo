package edu.uade.api.tpo.model;

import edu.uade.api.tpo.controller.SistemaPublicaciones;
import edu.uade.api.tpo.exceptions.BusinessException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

	public void ofertar(float monto, Usuario usuario, DatosPago datosPago) throws BusinessException {
		if (estado != Estado.A) {
			throw new BusinessException("La publicacion no está activa!");
		}
		if (!mediosPago.contains(datosPago.getMedioPago())) {
			throw new BusinessException("El medio de pago elegido no está disponible en esta publicación!");
		}
		if(monto <= getPrecioActual()) {
			throw new BusinessException("El monto ofertado es menor que el precio actual");
		}

		Oferta oferta = new Oferta(monto, usuario.getId(), id);
		ofertas.add(0, oferta);
		SistemaPublicaciones.getInstance().modificarSubasta(this);

		setChanged();
		notifyObservers(oferta);
	}

	private float getPrecioActual() {
		if(this.ofertas == null || this.ofertas.isEmpty()) {
			return this.getPrecioInicial();
		} else {
			return this.ofertas.get(0).getMonto();
		}
	}

	public Oferta obtenerMayorOferta() {
		return this.ofertas.get(0);
	}

	public boolean hasEnded() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.getFechaDesde());
		cal.add(Calendar.DAY_OF_MONTH, this.diasVigencia);
		return cal.getTime().compareTo(new Date()) <= 0;
	}
}
