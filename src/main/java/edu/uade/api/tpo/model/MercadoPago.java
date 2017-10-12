package edu.uade.api.tpo.model;

import edu.uade.api.tpo.controller.SistemaInterfazEntidadesRecaudadoras;

public class MercadoPago implements EntidadRecaudadora<CompraTarjetaCredito> {

	@Override
	public void informarPago(CompraTarjetaCredito compraTarjetaCredito) {
		SistemaInterfazEntidadesRecaudadoras.getInstance().procesarPagoTarjetaCredito(compraTarjetaCredito);
	}
}
