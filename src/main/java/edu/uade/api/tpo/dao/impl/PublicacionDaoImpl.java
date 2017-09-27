package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractGenericDao;
import edu.uade.api.tpo.dao.GenericDao;
import edu.uade.api.tpo.model.Publicacion;
import edu.uade.api.tpo.util.UUIDUtils;

import java.sql.*;

public class PublicacionDaoImpl extends AbstractGenericDao<Publicacion> {

    private static GenericDao<Publicacion> instance;

    private PublicacionDaoImpl() {
    }

    public static GenericDao<Publicacion> getInstance() {
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
        ps.setString(2, publicacion.getUsuario().getId());
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
        if(rs.first()) {
            publicacion = new Publicacion();
            publicacion.setId(rs.getString("publicacion_id"));
            publicacion.setUsuario(UsuarioDaoImpl.getInstance().findById(rs.getString("usuario_id")));
            publicacion.setFechaDesde(rs.getTimestamp("fecha_desde"));
            publicacion.setFechaHasta(rs.getTimestamp("fecha_hasta"));
            publicacion.setPrecio(rs.getFloat("precio"));
            publicacion.setEstado(rs.getString("estado").charAt(0));
            publicacion.setArticulo(ArticuloDaoImpl.getInstance().findById(rs.getString("articulo_id")));
            publicacion.setComision(rs.getFloat("comision"));
        }

        return publicacion;
    }

    @Override
    public PreparedStatement update(Publicacion publicacion, Connection conn) throws SQLException {
        String query = "UPDATE " + schema + ".publicaciones SET usuario_id = ?, fecha_desde = ?, fecha_hasta = ?, precio = ?, estado = ?, articulo_id = ?, comision = ? WHERE publicacion_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, publicacion.getUsuario().getId());
        ps.setTimestamp(2, new Timestamp(publicacion.getFechaDesde().getTime()));
        ps.setTimestamp(3, new Timestamp(publicacion.getFechaHasta().getTime()));
        ps.setFloat(4, publicacion.getPrecio());
        ps.setString(5, String.valueOf(publicacion.getEstado()));
        ps.setString(6, publicacion.getArticulo().getId());
        ps.setFloat(7, publicacion.getComision());
        ps.setString(8, publicacion.getId());

        return ps;
    }
}
