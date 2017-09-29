package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractDao;
import edu.uade.api.tpo.dao.GenericDao;
import edu.uade.api.tpo.model.Servicio;
import edu.uade.api.tpo.util.UUIDUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicioDaoImpl extends AbstractDao<Servicio> {

    private static GenericDao<Servicio> instance;

    private ServicioDaoImpl() {
    }

    public static GenericDao<Servicio> getInstance() {
        if (instance == null) {
            instance = new ServicioDaoImpl();
        }
        return instance;
    }

    @Override
    public PreparedStatement findById(String id, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".servicios WHERE servicio_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, id);
        return ps;
    }

    @Override
    public PreparedStatement create(Servicio servicio, Connection conn) throws SQLException {
        String query = "INSERT INTO " + schema + ".servicios VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, UUIDUtils.generate());
        ps.setString(2, servicio.getNombre());
        ps.setString(3, servicio.getDescripcion());
        ps.setString(4, servicio.toImagesTokenized());
        ps.setString(5, servicio.toCertificadosTokenized());
        return ps;
    }

    @Override
    public PreparedStatement update(Servicio servicio, Connection conn) throws SQLException {
        return null;
    }

    @Override
    public Servicio map(ResultSet rs) throws SQLException {
        return null;
    }
}
