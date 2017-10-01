package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractManyToOneDao;
import edu.uade.api.tpo.dao.ManyToOneDao;
import edu.uade.api.tpo.model.Publicacion;
import edu.uade.api.tpo.util.UUIDUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicacionDaoImpl extends AbstractManyToOneDao<Publicacion> {

    private static ManyToOneDao<Publicacion> instance;

    private PublicacionDaoImpl() {
    }

    public static ManyToOneDao<Publicacion> getInstance() {
        if (instance == null) {
            instance = new PublicacionDaoImpl();
        }
        return instance;
    }

    @Override
    public PreparedStatement create(Publicacion publicacion, Connection conn) throws SQLException {
        String query = "INSERT INTO " + schema + ".publicaciones VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, UUIDUtils.generate());
        ps.setString(2, publicacion.getUsuarioId());
        ps.setTimestamp(3, new Timestamp(publicacion.getFechaDesde().getTime()));
        ps.setTimestamp(4, new Timestamp(publicacion.getFechaHasta().getTime()));
        ps.setFloat(5, publicacion.getPrecio());
        ps.setString(6, String.valueOf(publicacion.getEstado()));
        ps.setString(7, publicacion.getArticulo().getId());
        ps.setFloat(8, publicacion.getComision());
        return ps;
    }

    @Override
    public PreparedStatement findById(String id, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".publicaciones WHERE publicacion_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, id);
        return ps;
    }

    @Override
    public Publicacion map(ResultSet rs) throws SQLException {
        Publicacion publicacion = null;
        if (rs.first()) {
            publicacion = mapRow(rs);
        }

        return publicacion;
    }

    @Override
    public PreparedStatement update(Publicacion publicacion, Connection conn) throws SQLException {
        String query = "UPDATE " + schema + ".publicaciones SET usuario_id = ?, fecha_desde = ?, fecha_hasta = ?, precio = ?, estado = ?, articulo_id = ?, comision = ? WHERE publicacion_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, publicacion.getUsuarioId());
        ps.setTimestamp(2, new Timestamp(publicacion.getFechaDesde().getTime()));
        ps.setTimestamp(3, new Timestamp(publicacion.getFechaHasta().getTime()));
        ps.setFloat(4, publicacion.getPrecio());
        ps.setString(5, String.valueOf(publicacion.getEstado()));
        ps.setString(6, publicacion.getArticulo().getId());
        ps.setFloat(7, publicacion.getComision());
        ps.setString(8, publicacion.getId());

        return ps;
    }

    @Override
    public PreparedStatement findManyBy(String field, String value, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".publicaciones WHERE " + field + " = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, value);
        return ps;
    }

    @Override
    public List<Publicacion> mapMany(ResultSet rs) throws SQLException {
        List<Publicacion> publicaciones = new ArrayList<>();
        while (rs.next()) {
            publicaciones.add(mapRow(rs));
        }
        return publicaciones;
    }

    private Publicacion mapRow(ResultSet rs) throws SQLException {
        Publicacion publicacion = new Publicacion();
        publicacion.setId(rs.getString("publicacion_id"));
        publicacion.setUsuarioId(rs.getString("usuario_id"));
        publicacion.setFechaDesde(rs.getTimestamp("fecha_desde"));
        publicacion.setFechaHasta(rs.getTimestamp("fecha_hasta"));
        publicacion.setPrecio(rs.getFloat("precio"));
        publicacion.setEstado(rs.getString("estado").charAt(0));
        publicacion.setArticulo(ArticuloDaoImpl.getInstance().findById(rs.getString("articulo_id")));
        publicacion.setComision(rs.getFloat("comision"));
        return publicacion;
    }
}
