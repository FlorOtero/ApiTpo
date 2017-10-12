package edu.uade.api.tpo.controller;

import edu.uade.api.tpo.Config;
import edu.uade.api.tpo.exceptions.BusinessException;
import edu.uade.api.tpo.exceptions.InvalidPasswordException;
import edu.uade.api.tpo.model.Comision;
import edu.uade.api.tpo.model.Publicacion;
import edu.uade.api.tpo.model.Transaccion;
import edu.uade.api.tpo.model.Usuario;

public class SistemaCuentaCorriente {

    private static final float porcentaje = 0.03f;
	private static SistemaCuentaCorriente instance = null;
	
	private SistemaCuentaCorriente() {};
	
	public static SistemaCuentaCorriente getInstance() {
        if(instance == null){
            instance = new SistemaCuentaCorriente();
        }
        return instance;
    }
	
/* *
 * logica de actualizacion del saldo
 * diferenciar las comisiones
 * tipoOperacion -> comision, venta, compra
 * donde almacenar el valor de la comision
 *
 * */
	public void actualizar(Publicacion publicacion, Usuario comprador, Transaccion tr) throws BusinessException, InvalidPasswordException {
		
		//agregamos la transaccion y las comisiones a las respectivas cuentas corrientes
		Usuario vendedor = SistemaUsuarios.getInstance().buscarUsuarioById(publicacion.getUsuarioId());
		Comision c = new Comision(publicacion.getPrecio() * Float.parseFloat(Config.getProperty("porcentajeComision")));
	
		vendedor.getCuentaCorriente().addComision(c);
		vendedor.getCuentaCorriente().addTransaccion(tr);
		comprador.getCuentaCorriente().addTransaccion(tr);
		
		
		//modificamos los usuarios para actualizar sus ctas ctes
		SistemaUsuarios.getInstance().modificarUsuario(comprador);
		SistemaUsuarios.getInstance().modificarUsuario(vendedor);
	}
	
	
}
