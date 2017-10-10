package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractDao;
import edu.uade.api.tpo.dao.GenericDao;
import edu.uade.api.tpo.model.EntidadRecaudadora;
import edu.uade.api.tpo.model.EntidadRecaudadoraFactory;

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
            entidad = EntidadRecaudadoraFactory.getEntidadRecaudadora(rs.getString("nombre"));
        }
        return entidad;
    }

    @Override
    public PreparedStatement update(EntidadRecaudadora entidadRecaudadora, Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Unsupported operation update EntidadRecaudadora!");
    }

    @Override
    public PreparedStatement create(EntidadRecaudadora entidadRecaudadora, Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Unsupported operation create EntidadRecaudadora!");
    }

    @Override
    public PreparedStatement findBy(String field, String value, Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Find by is not supported on class EntidadRecaudadora!");
    }
    
    @Override
    public void delete(EntidadRecaudadora t) throws SQLException {
    	throw new UnsupportedOperationException("Delete is not supported on class EntidadRecaudadora!");
    }
}
