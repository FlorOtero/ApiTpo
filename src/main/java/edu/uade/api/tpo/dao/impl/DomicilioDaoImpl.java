package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractDao;
import edu.uade.api.tpo.dao.GenericDao;
import edu.uade.api.tpo.model.Domicilio;
import edu.uade.api.tpo.util.UUIDUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DomicilioDaoImpl extends AbstractDao<Domicilio> {

    private static GenericDao<Domicilio> instance;

    private DomicilioDaoImpl() {

    }

    public static GenericDao<Domicilio> getInstance() {
        if (instance == null) {
            instance = new DomicilioDaoImpl();
        }
        return instance;
    }

    @Override
    public PreparedStatement findById(String id, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".domicilios WHERE domicilio_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, id);
        return ps;
    }

    @Override
    public PreparedStatement create(Domicilio domicilio, Connection conn) throws SQLException {
        String query = "INSERT INTO " + schema + ".domicilios VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, domicilio.getId());
        ps.setString(2, domicilio.getlinea1());
        ps.setString(3, domicilio.getlinea2());
        ps.setString(4, domicilio.getCp());
        ps.setString(5, domicilio.getCiudad());
        ps.setString(6, domicilio.getProvincia());
        return ps;
    }

    @Override
    public PreparedStatement update(Domicilio domicilio, Connection conn) throws SQLException {
        String query = "UPDATE " + schema + ".domicilios SET linea1 = ?, linea2 = ?, cp = ?, ciudad = ?, provincia = ? WHERE domicilio_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, domicilio.getlinea1());
        ps.setString(2, domicilio.getlinea2());
        ps.setString(3, domicilio.getCp());
        ps.setString(4, domicilio.getCiudad());
        ps.setString(5, domicilio.getProvincia());
        ps.setString(6, domicilio.getId());
        return ps;
    }

    @Override
    public Domicilio map(ResultSet rs) throws SQLException {
        Domicilio domicilio = null;
        if (rs.first()) {
            domicilio = new Domicilio();
            domicilio.setId(rs.getString("domicilio_id"));
            domicilio.setlinea1(rs.getString("linea1"));
            domicilio.setlinea2(rs.getString("linea2"));
            domicilio.setCp(rs.getString("cp"));
            domicilio.setCiudad(rs.getString("ciudad"));
            domicilio.setProvincia(rs.getString("provincia"));
        }
        return domicilio;
    }

    @Override
    public PreparedStatement findBy(String field, String value, Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Find by is not supported on class Domicilio!");
    }
    
    @Override
    public void delete(Domicilio t) throws SQLException {
    	throw new UnsupportedOperationException("Delete is not supported on class Domicilio!");
    }
}
