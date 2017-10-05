package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractDao;
import edu.uade.api.tpo.dao.GenericDao;
import edu.uade.api.tpo.model.Servicio;
import edu.uade.api.tpo.model.TipoContratacion;
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
        ps.setString(1, servicio.getId());
        ps.setString(2, servicio.getNombre());
        ps.setString(3, servicio.getDescripcion());
        ps.setString(4, servicio.toImagesTokenized());
        ps.setString(5, servicio.toCertificadosTokenized());
        ps.setString(6, servicio.getContratacion().toString());
        return ps;
    }

    @Override
    public PreparedStatement update(Servicio servicio, Connection conn) throws SQLException {
        String query = "UPDATE " + schema + ".servicios SET nombre = ?, descripcion = ?, imagenes = ?, certificados = ?, tipo_contratacion = ? WHERE servicio_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, servicio.getNombre());
        ps.setString(2, servicio.getDescripcion());
        ps.setString(3, servicio.toImagesTokenized());
        ps.setString(4, servicio.toCertificadosTokenized());
        ps.setString(5, servicio.getContratacion().toString());
        ps.setString(6, servicio.getId());
        return ps;
    }

    @Override
    public Servicio map(ResultSet rs) throws SQLException {
        Servicio servicio = null;
        if (rs.first()) {
            servicio = new Servicio();
            servicio.setId(rs.getString("servicio_id"));
            servicio.setNombre(rs.getString("nombre"));
            servicio.setDescripcion(rs.getString("descripcion"));
            servicio.fromImagesTokenized(rs.getString("imagenes"));
            servicio.fromCertificadosTokenized(rs.getString("certificados"));
            servicio.setContratacion(TipoContratacion.valueOf(rs.getString("tipo_contratacion")));
        }
        return servicio;
    }

    @Override
    public PreparedStatement findBy(String field, String value, Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Find by is not supported on class Servicio!");
    }
}
