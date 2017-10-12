package edu.uade.api.tpo.controller;

import edu.uade.api.tpo.dao.impl.TransaccionDaoImpl;
import edu.uade.api.tpo.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class SistemaTransacciones {
    private static SistemaTransacciones instance = null;
    private static final Logger logger = LoggerFactory.getLogger(SistemaTransacciones.class);

    private SistemaTransacciones() {
    }

    public static SistemaTransacciones getInstance() {
        if (instance == null) {
            instance = new SistemaTransacciones();
        }
        return instance;
    }

    public void crearTransaccion(Usuario contraparte, Publicacion publicacion, DatosPago datosPago) {
        Transaccion tr = null;
        switch (datosPago.getMedioPago()) {
            case EFECTIVO:
                publicacion.setEstado(Estado.I);
                tr = new CompraEfectivo(publicacion, contraparte);
                break;
            case TRANSFERENCIA_BANCARIA:
                publicacion.setEstado(Estado.P);
                tr = new CompraTransferenciaBancaria(publicacion, contraparte, datosPago.getNumeroCuenta());
                break;
            case TARJETA_CREDITO:
                publicacion.setEstado(Estado.P);
                tr = new CompraTarjetaCredito(publicacion, contraparte, datosPago.getNumeroTarjeta());
                break;
        }
        try {
            SistemaPublicaciones.getInstance().modificarPublicacion(publicacion);
            TransaccionDaoImpl.getInstance().create(tr);
            tr.ejecutar();
        } catch (Exception e) {
            logger.error("Error creando transaccion", e);
        }
    }

    public void aprobarTransaccion(Transaccion transaccion) {
        transaccion.setEstado(EstadoTransaccion.A);
        transaccion.getPublicacion().setEstado(Estado.I);
        try {
            SistemaPublicaciones.getInstance().modificarPublicacion(transaccion.getPublicacion());
            TransaccionDaoImpl.getInstance().update(transaccion);
            //Generar movimiento cuentaCorriente!!

            logger.info(">>> TRANSACCION GENERADA!! <<<");
        } catch (SQLException e) {
            logger.error("Error aprobando transaccion", e);
        }
    }

    public void rechazarTransaccion(Transaccion transaccion) {
        transaccion.setEstado(EstadoTransaccion.C);
        transaccion.getPublicacion().setEstado(Estado.A);
        try {
            SistemaPublicaciones.getInstance().modificarPublicacion(transaccion.getPublicacion());
            TransaccionDaoImpl.getInstance().update(transaccion);
            logger.info(">>> TRANSACCION RECHAZADA!! <<<");
        } catch (SQLException e) {
            logger.error("Error rechazando transaccion", e);
        }
    }

}
