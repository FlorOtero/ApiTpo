package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractDao;
import edu.uade.api.tpo.dao.GenericDao;
import edu.uade.api.tpo.model.Comision;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComisionDaoImpl extends AbstractDao<Comision> {

    private static GenericDao<Comision> instance;

    private ComisionDaoImpl() {
    }

    public static GenericDao<Comision> getInstance() {
        if (instance == null) {
            instance = new ComisionDaoImpl();
        }
        return instance;
    }

    @Override
    public PreparedStatement findById(String id, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".comisiones WHERE comision_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, id);
        return ps;
    }

    @Override
    public PreparedStatement create(Comision comision, Connection conn) throws SQLException {
        String query = "INSERT INTO " + schema + ".comisiones VALUES(?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, comision.getId());
        ps.setFloat(2, comision.getImporte());
        return ps;
    }

    @Override
    public PreparedStatement update(Comision comision, Connection conn) throws SQLException {
        String query = "UPDATE " + schema + ".comisiones SET importe = ? WHERE comision_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setFloat(1, comision.getImporte());
        ps.setString(2, comision.getId());
        return ps;
    }

    @Override
    public Comision map(ResultSet rs) throws SQLException {
        Comision comision = null;
        if (rs.first()) {
            comision = new Comision();
            comision.setId(rs.getString("comision_id"));
            comision.setImporte(rs.getFloat("importe"));
        }
        return comision;
    }

    @Override
    public void delete(Comision comision) throws SQLException {
        throw new UnsupportedOperationException("Delete is not supported on Comision");
    }

    @Override
    public PreparedStatement findBy(String field, String value, Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Find by is not supported on Comision");
    }
}
