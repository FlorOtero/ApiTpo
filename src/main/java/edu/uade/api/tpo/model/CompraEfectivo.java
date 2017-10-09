package edu.uade.api.tpo.model;

public class CompraEfectivo extends Transaccion {
	
	public CompraEfectivo() {}
	
	public CompraEfectivo(Publicacion publicacion, Usuario contraparte) {
		super(publicacion, contraparte);
		// TODO Auto-generated constructor stub
	}

	public void pagar() {
		//restamos de la cta cte del comprador y sumamos en la del vendedor
		//+ comisiones
		float saldoComprador = this.getContraparte().getCuentaCorriente().getSaldo();
		String idVendedor = this.getPublicacion().getUsuarioId();
		Usuario vendedor = SistemaUsuarios.getInstance().buscarUsuarioById(idVendedor);
		float saldoVendedor = vendedor.getCuentaCorriente().getSaldo();
		
		//restamos de la cta cte comprador el valor de la publicacion
		saldoComprador = saldoComprador - this.getPublicacion().getPrecio();
		//sumamos de la cta cte vendedor el valor de la venta
		saldoVendedor = saldoVendedor + this.getPublicacion().getPrecio();
		//restamos de la cta cte vendedor el valor de la comision
		saldoVendedor = saldoVendedor - (this.getPublicacion().getPrecio() * this.getPublicacion().getComision());
		
		SistemaUsuarios.getInstance().buscarUsuario(this.getContraparte().getNombreUsuario()).getCuentaCorriente().setSaldo(saldoComprador);
		SistemaUsuarios.getInstance().buscarUsuario(vendedor.getNombreUsuario()).getCuentaCorriente().setSaldo(saldoVendedor);

	}
}
