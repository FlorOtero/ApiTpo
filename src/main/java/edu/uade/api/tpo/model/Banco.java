package edu.uade.api.tpo.model;

import edu.uade.api.tpo.controller.SistemaInterfazEntidadesRecaudadoras;

public class Banco implements EntidadRecaudadora<CompraTransferenciaBancaria> {

	@Override
	public void informarPago(CompraTransferenciaBancaria compraTransferenciaBancaria) {
		SistemaInterfazEntidadesRecaudadoras.getInstance().procesarPagoTransferenciaBancaria(compraTransferenciaBancaria);
	}
}
