package edu.uade.api.tpo.controller;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uade.api.tpo.Config;
import edu.uade.api.tpo.exceptions.BusinessException;
import edu.uade.api.tpo.exceptions.InvalidPasswordException;
import edu.uade.api.tpo.model.Comision;
import edu.uade.api.tpo.model.EstadoTransaccion;
import edu.uade.api.tpo.model.Publicacion;
import edu.uade.api.tpo.model.Transaccion;
import edu.uade.api.tpo.model.Usuario;

public class SistemaCuentaCorriente {

	private static SistemaCuentaCorriente instance = null;
	private static final Logger logger = LoggerFactory.getLogger(SistemaCuentaCorriente.class);

	private SistemaCuentaCorriente() {
	}

	public static SistemaCuentaCorriente getInstance() {
		if (instance == null) {
			instance = new SistemaCuentaCorriente();
		}
		return instance;
	}

	public void actualizarSaldo(Transaccion tr) throws BusinessException, InvalidPasswordException {

		if (!(tr.getEstado() == EstadoTransaccion.A)) {
			throw new BusinessException("La transaccion aun no ha sido aprobada");
		}
		
		try {

			Usuario vendedor = SistemaUsuarios.getInstance().buscarUsuarioById(tr.getPublicacion().getUsuarioId());
			Usuario comprador = tr.getContraparte();
			
			// agregamos la transaccion y las comisiones a las respectivas ctas
			Comision c = tr.getPublicacion().getComision();
			//TODO HACER LA PERSISTENCIA DEL ID DE TRANSACCION EN LA COMISION
			c.setIdTransaccion(tr.getId());
			vendedor.getCuentaCorriente().addComision(c);
			vendedor.getCuentaCorriente().addTransaccion(tr);
			comprador.getCuentaCorriente().addTransaccion(tr);

			// actualizamos los saldos
			float saldoVendedor = vendedor.getCuentaCorriente().getSaldo();
			float saldoComprador = comprador.getCuentaCorriente().getSaldo();

			vendedor.getCuentaCorriente().setSaldo(saldoVendedor + tr.getPublicacion().getPrecio() - tr.getPublicacion().getComision().getImporte());
			comprador.getCuentaCorriente().setSaldo(saldoComprador - tr.getPublicacion().getPrecio());

			// modificamos los usuarios para actualizar sus ctas ctes
			SistemaUsuarios.getInstance().modificarUsuario(comprador);
			SistemaUsuarios.getInstance().modificarUsuario(vendedor);
			
		} catch (Exception e) {
			logger.error("Error actualizando saldo", e);
		}

	}
	
	

}
