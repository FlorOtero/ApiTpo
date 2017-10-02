package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractDao;
import edu.uade.api.tpo.dao.GenericDao;
import edu.uade.api.tpo.model.CuentaCorriente;
import edu.uade.api.tpo.util.UUIDUtils;

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
        String query = "SELECT * FROM " + schema + ".cuentas_corrientes WHERE cuenta_corriente_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, id);
        return ps;
    }

    @Override
    public PreparedStatement create(CuentaCorriente cuentaCorriente, Connection conn) throws SQLException {
        String query = "INSERT INTO " + schema + ".cuentas_corrientes VALUES(?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, UUIDUtils.generate());
        ps.setFloat(2, cuentaCorriente.getSaldo());
        return ps;
    }

    @Override
    public PreparedStatement update(CuentaCorriente cuentaCorriente, Connection conn) throws SQLException {
        String query = "UPDATE " + schema + ".cuentas_corrientes SET saldo = ? WHERE cuenta_corriente_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setFloat(1, cuentaCorriente.getSaldo());
        ps.setString(2, cuentaCorriente.getId());
        return ps;
    }

    @Override
    public CuentaCorriente map(ResultSet rs) throws SQLException {
        CuentaCorriente cuentaCorriente = null;
        if (rs.first()) {
            cuentaCorriente = new CuentaCorriente();
            cuentaCorriente.setId(rs.getString("cuenta_corriente_id"));
            cuentaCorriente.setSaldo(rs.getFloat("saldo"));
            cuentaCorriente.setTransacciones(TransaccionDaoImpl.getInstance().findByCuentaCorriente(cuentaCorriente.getId()));
        }
        return cuentaCorriente;
    }

    @Override
    public PreparedStatement findBy(String field, String value, Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Find by is not supported on class CuentaCorriente!");
    }
}
