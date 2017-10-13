package edu.uade.api.tpo.controller;

import edu.uade.api.tpo.model.CompraTarjetaCredito;
import edu.uade.api.tpo.model.CompraTransferenciaBancaria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class SistemaInterfazEntidadesRecaudadoras {

    private static SistemaInterfazEntidadesRecaudadoras instance;
    private static final Logger logger = LoggerFactory.getLogger(SistemaInterfazEntidadesRecaudadoras.class);
    private static final long APPROVAL_WAIT_TIME = 5000;

    private SistemaInterfazEntidadesRecaudadoras() {
    }

    public static SistemaInterfazEntidadesRecaudadoras getInstance() {
        if (instance == null) {
            instance = new SistemaInterfazEntidadesRecaudadoras();
        }
        return instance;
    }

    public void procesarPagoTransferenciaBancaria(CompraTransferenciaBancaria compra) {
        try {
            logger.info(">>> Autorizando pago <<<");
            TimeUnit.MILLISECONDS.sleep(APPROVAL_WAIT_TIME);
            boolean approved = getRandom() != 0;
            if(approved) {
                SistemaTransacciones.getInstance().aprobarTransaccion(compra);
            } else {
                SistemaTransacciones.getInstance().rechazarTransaccion(compra);
            }
        } catch (Exception e) {
            logger.error("Error procesando el pago por transferencia bancaria", e);
        }
    }

    public void procesarPagoTarjetaCredito(CompraTarjetaCredito compra) {
        try {
            logger.info(">>> Autorizando pago <<<");
            TimeUnit.MILLISECONDS.sleep(APPROVAL_WAIT_TIME);
            boolean approved = getRandom() != 0;
            if(approved) {
                SistemaTransacciones.getInstance().aprobarTransaccion(compra);
            } else {
                SistemaTransacciones.getInstance().rechazarTransaccion(compra);
            }
        } catch (Exception e) {
            logger.error("Error procesando el pago por transferencia bancaria", e);
        }
    }

    private int getRandom() {
        return (int) (Math.random() * 4);
    }
}
