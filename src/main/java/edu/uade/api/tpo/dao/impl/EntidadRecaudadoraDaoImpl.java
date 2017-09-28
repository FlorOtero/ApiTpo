package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractDao;
import edu.uade.api.tpo.dao.GenericDao;
import edu.uade.api.tpo.model.Banco;
import edu.uade.api.tpo.model.EntidadRecaudadora;
import edu.uade.api.tpo.model.MercadoPago;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntidadRecaudadoraDaoImpl extends AbstractDao<EntidadRecaudadora> {

    private static GenericDao<EntidadRecaudadora> instance;

    private EntidadRecaudadoraDaoImpl() {

    }

    public static GenericDao<EntidadRecaudadora> getInstance() {
        if (instance == null) {
            instance = new EntidadRecaudadoraDaoImpl();
        }
        return instance;
    }

    @Override
    public PreparedStatement findById(String id, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".entidades_recaudadoras WHERE entidad_recaudadora_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, id);
        return ps;
    }

    @Override
    public EntidadRecaudadora map(ResultSet rs) throws SQLException {
        EntidadRecaudadora entidad = null;
        if (rs.first()) {
            if (rs.getString("nombre").equals("Banco")) {
                entidad = new Banco();
            } else {
                entidad = new MercadoPago();
            }
        }
        return entidad;
    }

    @Override
    public PreparedStatement update(EntidadRecaudadora entidadRecaudadora, Connection conn) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement create(EntidadRecaudadora entidadRecaudadora, Connection conn) throws SQLException {
        return null;
    }
}
