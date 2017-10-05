package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractManyToOneDao;
import edu.uade.api.tpo.dao.ManyToOneDao;
import edu.uade.api.tpo.model.Calificacion;
import edu.uade.api.tpo.util.UUIDUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalificacionDaoImpl extends AbstractManyToOneDao<Calificacion> {

    private static ManyToOneDao<Calificacion> instance;

    private CalificacionDaoImpl() {
    }

    public static ManyToOneDao<Calificacion> getInstance() {
        if (instance == null) {
            instance = new CalificacionDaoImpl();
        }
        return instance;
    }

    @Override
    public PreparedStatement findById(String id, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".calificaciones WHERE calificacion_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, id);
        return ps;
    }

    @Override
    public PreparedStatement create(Calificacion calificacion, Connection conn) throws SQLException {
        String query = "INSERT INTO " + schema + ".calificaciones VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, calificacion.getId());
        ps.setInt(2, calificacion.getCalificacion());
        ps.setString(3, calificacion.getObservaciones());
        ps.setTimestamp(4, new Timestamp(calificacion.getFecha().getTime()));
        ps.setString(5, String.valueOf(calificacion.getEstado()));
        ps.setString(6, calificacion.getTransaccion().getId());
        ps.setString(7, calificacion.getUsuarioId());

        return ps;
    }

    @Override
    public PreparedStatement update(Calificacion calificacion, Connection conn) throws SQLException {
        String query = "UPDATE " + schema + ".calificaciones VALUES calificacion = ?, observaciones = ?, fecha = ?, estado = ?, transaccion_id = ?, usuario_id = ? WHERE calificacion_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, calificacion.getCalificacion());
        ps.setString(2, calificacion.getObservaciones());
        ps.setTimestamp(3, new Timestamp(calificacion.getFecha().getTime()));
        ps.setString(4, String.valueOf(calificacion.getEstado()));
        ps.setString(5, calificacion.getTransaccion().getId());
        ps.setString(6, calificacion.getUsuarioId());
        ps.setString(7, calificacion.getId());
        return null;
    }

    @Override
    public Calificacion map(ResultSet rs) throws SQLException {
        Calificacion calificacion = null;
        if (rs.first()) {
            calificacion = mapRow(rs);
        }
        return calificacion;
    }

    @Override
    public PreparedStatement findManyBy(String field, String value, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".calificaciones WHERE " + field + " = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, value);
        return ps;
    }

    @Override
    public List<Calificacion> mapMany(ResultSet rs) throws SQLException {
        List<Calificacion> calificaciones = new ArrayList<>();
        while (rs.next()) {
            calificaciones.add(mapRow(rs));
        }
        return calificaciones;
    }

    private Calificacion mapRow(ResultSet rs) throws SQLException {
        Calificacion calificacion = new Calificacion();
        calificacion.setId(rs.getString("calificacion_id"));
        calificacion.setCalificacion(rs.getInt("calificacion"));
        calificacion.setObservaciones(rs.getString("observaciones"));
        calificacion.setFecha(rs.getTimestamp("fecha"));
        calificacion.setEstado(rs.getString("estado").charAt(0));
        calificacion.setTransaccion(TransaccionDaoImpl.getInstance().findById(rs.getString("transaccion_id")));
        calificacion.setUsuarioId(rs.getString("usuario_id"));
        return calificacion;
    }

    @Override
    public PreparedStatement findBy(String field, String value, Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Find by is not supported on class Calificacion!");
    }
}
