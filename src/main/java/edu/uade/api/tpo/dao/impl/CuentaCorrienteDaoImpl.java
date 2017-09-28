package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractDao;
import edu.uade.api.tpo.dao.GenericDao;
import edu.uade.api.tpo.model.CuentaCorriente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CuentaCorrienteDaoImpl extends AbstractDao<CuentaCorriente> {

    private static GenericDao<CuentaCorriente> instance;

    private CuentaCorrienteDaoImpl() {
    }

    public static GenericDao<CuentaCorriente> getInstance() {
        if (instance == null) {
            instance = new CuentaCorrienteDaoImpl();
        }
        return instance;
    }

    @Override
    public PreparedStatement findById(String id, Connection conn) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement create(CuentaCorriente cuentaCorriente, Connection conn) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement update(CuentaCorriente cuentaCorriente, Connection conn) throws SQLException {
        return null;
    }

    @Override
    public CuentaCorriente map(ResultSet rs) throws SQLException {
        return null;
    }
}
