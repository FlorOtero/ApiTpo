package edu.uade.api.tpo.controller;

import edu.uade.api.tpo.dao.impl.CompraTarjetaCreditoDaoImpl;
import edu.uade.api.tpo.dao.impl.CompraTransferenciaBancariaDaoImpl;
import edu.uade.api.tpo.model.CompraTarjetaCredito;
import edu.uade.api.tpo.model.CompraTransferenciaBancaria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class SistemaInterfazEntidadesRecaudadoras {

    private static SistemaInterfazEntidadesRecaudadoras instance;
    private static final Logger logger = LoggerFactory.getLogger(SistemaInterfazEntidadesRecaudadoras.class);

    private SistemaInterfazEntidadesRecaudadoras() {}

    public static SistemaInterfazEntidadesRecaudadoras getInstance() {
        if(instance == null) {
            instance = new SistemaInterfazEntidadesRecaudadoras();
        }
        return instance;
    }

    public void procesarPagoTransferenciaBancaria(CompraTransferenciaBancaria compra) {
        try {
            CompraTransferenciaBancariaDaoImpl.getInstance().create(compra);
        } catch (SQLException e) {
            logger.error("Error procesando el pago por transferencia bancaria", e);
        }
    }

    public void procesarPagoTarjetaCredito(CompraTarjetaCredito compra) {
        try {
            CompraTarjetaCreditoDaoImpl.getInstance().create(compra);
        } catch (SQLException e) {
            logger.error("Error procesando el pago por transferencia bancaria", e);
        }
    }
}
