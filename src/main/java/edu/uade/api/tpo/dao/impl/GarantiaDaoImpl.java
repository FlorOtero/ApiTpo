package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractDao;
import edu.uade.api.tpo.dao.GenericDao;
import edu.uade.api.tpo.model.Garantia;
import edu.uade.api.tpo.model.TipoPeriodo;
import edu.uade.api.tpo.util.UUIDUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GarantiaDaoImpl extends AbstractDao<Garantia> {

    private static GenericDao<Garantia> instance;

    private GarantiaDaoImpl() {
    }

    public static GenericDao<Garantia> getInstance() {
        if (instance == null) {
            instance = new GarantiaDaoImpl();
        }
        return instance;
    }

    @Override
    public PreparedStatement findById(String id, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".garantias WHERE garantia_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, id);
        return ps;
    }

    @Override
    public PreparedStatement create(Garantia garantia, Connection conn) throws SQLException {
        String query = "INSERT INTO " + schema + ".garantias VALUES(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, UUIDUtils.generate());
        ps.setInt(2, garantia.getCantidad());
        ps.setString(3, garantia.getTipo().toString());
        return ps;
    }

    @Override
    public PreparedStatement update(Garantia garantia, Connection conn) throws SQLException {
        String query = "UPDATE " + schema + ".garantias SET cantidad = ?, tipo = ? WHERE garantia_id  = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, garantia.getCantidad());
        ps.setString(2, garantia.getTipo().toString());
        ps.setString(3, garantia.getId());
        return ps;
    }

    @Override
    public Garantia map(ResultSet rs) throws SQLException {
        Garantia garantia = null;
        if (rs.first()) {
            garantia = new Garantia();
            garantia.setId(rs.getString("garantia_id"));
            garantia.setCantidad(rs.getInt("cantidad"));
            garantia.setTipo(TipoPeriodo.valueOf(rs.getString("tipo")));
        }
        return garantia;
    }

    @Override
    public PreparedStatement findBy(String field, String value, Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Find by is not supported on class Garantia!");
    }
}
