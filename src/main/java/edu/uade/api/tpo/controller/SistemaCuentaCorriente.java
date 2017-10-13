package edu.uade.api.tpo.controller;

import edu.uade.api.tpo.exceptions.BusinessException;
import edu.uade.api.tpo.exceptions.InvalidPasswordException;
import edu.uade.api.tpo.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

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
            //TODO setear el importe que corresponda
            Comision comision = new Comision(123);
            tr.setComision(comision);

            // actualizamos los saldos
            float saldoVendedor = vendedor.getCuentaCorriente().getSaldo();
            float saldoComprador = comprador.getCuentaCorriente().getSaldo();

            vendedor.getCuentaCorriente().setSaldo(saldoVendedor + tr.getPublicacion().getPrecio() - comision.getImporte());
            comprador.getCuentaCorriente().setSaldo(saldoComprador - tr.getPublicacion().getPrecio());

            // modificamos los usuarios para actualizar sus ctas ctes
            SistemaUsuarios.getInstance().modificarUsuario(comprador);
            SistemaUsuarios.getInstance().modificarUsuario(vendedor);

            // actualizo la transaccion ya que guarde su comision
            SistemaTransacciones.getInstance().actualizarTransaccion(tr);

        } catch (Exception e) {
            logger.error("Error actualizando saldo", e);
        }

    }

    public List<Comision> listarComisiones(String nombreUsuario) throws BusinessException {

        Usuario usuario = SistemaUsuarios.getInstance().buscarUsuario(nombreUsuario);

        if (!usuario.getCuentaCorriente().hasComisiones()) {
            throw new BusinessException("No hay comisiones para mostrar");
        }

        return usuario.getCuentaCorriente().getComisiones();

    }

    public List<ItemCtaCte> consultarMovimientos(String nombreUsuario) throws BusinessException {

        List<ItemCtaCte> movimientos = new ArrayList<>();
        Usuario usuario = SistemaUsuarios.getInstance().buscarUsuario(nombreUsuario);

        if (!usuario.getCuentaCorriente().hasTransacciones()) {
            throw new BusinessException("No hay movimientos en la cuenta corriente");
        }

        for (Transaccion tr : usuario.getCuentaCorriente().getTransacciones()) {

            ItemCtaCte item = new ItemCtaCte(tr.getId());
            //si fue una compra...
            if (tr.getContraparte().getNombreUsuario().equals(nombreUsuario)) {
                //no se cobra comision x compra
                item.setComision(false);
                //mostramos un descuento en la cuenta del comprador
                item.setMonto(tr.getPublicacion().getPrecio() * -1);
            } else {
                //fue una venta, mostramos el credito en la cta del vendedor
                item.setMonto(tr.getPublicacion().getPrecio());
                //al ser venta hubo comision
                item.setComision(true);
            }

            if (item.isComision()) {
                //si hubo comision, tenemos que reflejar el descuento
                ItemCtaCte com = new ItemCtaCte(tr.getId());
                com.setMonto(tr.getComision().getImporte() * -1);
                movimientos.add(com);
            }
            movimientos.add(item);
        }

        return movimientos;

    }


}
