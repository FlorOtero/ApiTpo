package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.GenericDao;
import edu.uade.api.tpo.model.CompraEfectivo;
import edu.uade.api.tpo.model.CompraTarjetaCredito;
import edu.uade.api.tpo.model.CompraTransferenciaBancaria;
import edu.uade.api.tpo.model.Transaccion;

import java.sql.SQLException;

public class TransaccionDaoImpl implements GenericDao<Transaccion> {

    private static GenericDao<Transaccion> instance;

    private TransaccionDaoImpl() {
    }

    public static GenericDao<Transaccion> getInstance() {
        if (instance == null) {
            instance = new TransaccionDaoImpl();
        }
        return instance;
    }

    @Override
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

    @Override
    public void create(Transaccion transaccion) throws SQLException {
        if (transaccion instanceof CompraEfectivo) {
            CompraEfectivoDaoImpl.getInstance().create((CompraEfectivo) transaccion);
        } else if (transaccion instanceof CompraTarjetaCredito) {
            CompraTarjetaCreditoDaoImpl.getInstance().create((CompraTarjetaCredito) transaccion);
        } else if (transaccion instanceof CompraTransferenciaBancaria) {
            CompraTransferenciaBancariaDaoImpl.getInstance().create((CompraTransferenciaBancaria) transaccion);
        }
    }

    @Override
    public void update(Transaccion transaccion) throws SQLException {
        if (transaccion instanceof CompraEfectivo) {
            CompraEfectivoDaoImpl.getInstance().update((CompraEfectivo) transaccion);
        } else if (transaccion instanceof CompraTarjetaCredito) {
            CompraTarjetaCreditoDaoImpl.getInstance().update((CompraTarjetaCredito) transaccion);
        } else if (transaccion instanceof CompraTransferenciaBancaria) {
            CompraTransferenciaBancariaDaoImpl.getInstance().update((CompraTransferenciaBancaria) transaccion);
        }
    }
}
