package edu.uade.api.tpo.model;

import edu.uade.api.tpo.controller.SistemaUsuarios;
import edu.uade.api.tpo.exceptions.BusinessException;
import edu.uade.api.tpo.exceptions.InvalidPasswordException;

public class CompraEfectivo extends Transaccion {
	
	public CompraEfectivo() {}
	
	public CompraEfectivo(Publicacion publicacion, Usuario contraparte) {
		super(publicacion, contraparte);
		this.setEstado(EstadoTransaccion.A);
		// TODO Auto-generated constructor stub
	}

	public void pagar() throws BusinessException, InvalidPasswordException {
		//restamos de la cta cte del comprador y sumamos en la del vendedor
		//+ comisiones
		float saldoComprador = this.getContraparte().getCuentaCorriente().getSaldo();
		Usuario vendedor = SistemaUsuarios.getInstance().buscarUsuarioById(this.getPublicacion().getUsuarioId());
		float saldoVendedor = vendedor.getCuentaCorriente().getSaldo();
		
		//restamos de la cta cte comprador el valor de la publicacion
		saldoComprador = saldoComprador - this.getPublicacion().getPrecio();
		//sumamos de la cta cte vendedor el valor de la venta
		saldoVendedor = saldoVendedor + this.getPublicacion().getPrecio();
		//restamos de la cta cte vendedor el valor de la comision
		saldoVendedor = saldoVendedor - (this.getPublicacion().getPrecio() * this.getPublicacion().getComision());
		
		//seteamos los objetos con sus saldos correspondientes
		this.getContraparte().getCuentaCorriente().setSaldo(saldoComprador);
		vendedor.getCuentaCorriente().setSaldo(saldoVendedor);
		
		//modificamos los usuarios
		SistemaUsuarios.getInstance().modificarUsuario(this.getContraparte());
		SistemaUsuarios.getInstance().modificarUsuario(vendedor);

	}
}
