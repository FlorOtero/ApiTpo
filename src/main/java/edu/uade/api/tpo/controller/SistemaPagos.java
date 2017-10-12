package edu.uade.api.tpo.controller;

import edu.uade.api.tpo.model.Transaccion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SistemaPagos {

    private static SistemaPagos instance;
    private static final Logger logger = LoggerFactory.getLogger(SistemaPagos.class);

    private SistemaPagos() {
    }

    public static SistemaPagos getInstance() {
        if (instance == null) {
            instance = new SistemaPagos();
        }
        return instance;
    }

    public void pagar(Transaccion transaccion) {
        try {
            transaccion.pagar();
            //Lo ultimo deberia ser aprobar la transaccion
            SistemaTransacciones.getInstance().aprobarTransaccion(transaccion);
        } catch (Exception e) {
            logger.error("Error ejecutando el pago de la transaccion", e);
        }
    }
}
