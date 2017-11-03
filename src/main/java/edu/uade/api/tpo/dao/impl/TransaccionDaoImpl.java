package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.model.CompraEfectivo;
import edu.uade.api.tpo.model.CompraTarjetaCredito;
import edu.uade.api.tpo.model.CompraTransferenciaBancaria;
import edu.uade.api.tpo.model.Transaccion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransaccionDaoImpl {

    private static final String CTA_CTE_FIELD_NAME = "cuenta_corriente_id";
    private static final String CONTRAPARTE_ID = "contraparte_id";

    private static TransaccionDaoImpl instance;

    private TransaccionDaoImpl() {
    }

    public static TransaccionDaoImpl getInstance() {
        if (instance == null) {
            instance = new TransaccionDaoImpl();
        }
        return instance;
    }

    public Transaccion findById(String id) throws SQLException {
        Transaccion transaccion = CompraEfectivoDaoImpl.getInstance().findById(id);
        if (transaccion == null) {
            transaccion = CompraTarjetaCreditoDaoImpl.getInstance().findById(id);
            if (transaccion == null) {
                transaccion = CompraTransferenciaBancariaDaoImpl.getInstance().findById(id);
            }
        }
        return transaccion;
    }

    public void create(Transaccion transaccion) throws SQLException {
        if (transaccion instanceof CompraEfectivo) {
            CompraEfectivoDaoImpl.getInstance().create((CompraEfectivo) transaccion);
        } else if (transaccion instanceof CompraTarjetaCredito) {
            CompraTarjetaCreditoDaoImpl.getInstance().create((CompraTarjetaCredito) transaccion);
        } else if (transaccion instanceof CompraTransferenciaBancaria) {
            CompraTransferenciaBancariaDaoImpl.getInstance().create((CompraTransferenciaBancaria) transaccion);
        }
    }

    public void update(Transaccion transaccion) throws SQLException {
        if (transaccion instanceof CompraEfectivo) {
            CompraEfectivoDaoImpl.getInstance().update((CompraEfectivo) transaccion);
        } else if (transaccion instanceof CompraTarjetaCredito) {
            CompraTarjetaCreditoDaoImpl.getInstance().update((CompraTarjetaCredito) transaccion);
        } else if (transaccion instanceof CompraTransferenciaBancaria) {
            CompraTransferenciaBancariaDaoImpl.getInstance().update((CompraTransferenciaBancaria) transaccion);
        }
    }

    public List<Transaccion> findByCuentaCorriente(String cuentaCorrienteId) throws SQLException {
        List<Transaccion> result = new ArrayList<>();
        result.addAll(Collections.unmodifiableList(CompraEfectivoDaoImpl.getInstance().findManyBy(CTA_CTE_FIELD_NAME, cuentaCorrienteId)));
        result.addAll(Collections.unmodifiableList(CompraTarjetaCreditoDaoImpl.getInstance().findManyBy(CTA_CTE_FIELD_NAME, cuentaCorrienteId)));
        result.addAll(Collections.unmodifiableList(CompraTransferenciaBancariaDaoImpl.getInstance().findManyBy(CTA_CTE_FIELD_NAME, cuentaCorrienteId)));
        return result;
    }

    public List<Transaccion> findByContraparte(String contraparteId) throws SQLException {
        List<Transaccion> result = new ArrayList<>();
        result.addAll(Collections.unmodifiableList(CompraEfectivoDaoImpl.getInstance().findManyBy(CONTRAPARTE_ID, contraparteId)));
        result.addAll(Collections.unmodifiableList(CompraTarjetaCreditoDaoImpl.getInstance().findManyBy(CONTRAPARTE_ID, contraparteId)));
        result.addAll(Collections.unmodifiableList(CompraTransferenciaBancariaDaoImpl.getInstance().findManyBy(CONTRAPARTE_ID, contraparteId)));
        return result;
    }
}
