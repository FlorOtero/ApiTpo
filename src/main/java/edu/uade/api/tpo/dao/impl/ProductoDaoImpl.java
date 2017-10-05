package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractDao;
import edu.uade.api.tpo.dao.GenericDao;
import edu.uade.api.tpo.model.Producto;
import edu.uade.api.tpo.util.UUIDUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDaoImpl extends AbstractDao<Producto> {

    private static GenericDao<Producto> instance;

    private ProductoDaoImpl() {
    }

    public static GenericDao<Producto> getInstance() {
        if (instance == null) {
            instance = new ProductoDaoImpl();
        }
        return instance;
    }

    @Override
    public PreparedStatement findById(String id, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".productos WHERE producto_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, id);
        return ps;
    }

    @Override
    public PreparedStatement create(Producto producto, Connection conn) throws SQLException {
        String query = "INSERT INTO " + schema + ".productos VALUES(?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, producto.getId());
        ps.setString(2, producto.getNombre());
        ps.setString(3, producto.getDescripcion());
        ps.setString(4, producto.toImagesTokenized());
        ps.setString(5, producto.getGarantia().getId());
        return ps;
    }

    @Override
    public PreparedStatement update(Producto producto, Connection conn) throws SQLException {
        String query = "UPDATE " + schema + ".productos SET nombre = ?, descripcion = ?, imagenes = ?, garantia_id = ? WHERE producto_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, producto.getNombre());
        ps.setString(2, producto.getDescripcion());
        ps.setString(3, producto.toImagesTokenized());
        ps.setString(4, producto.getGarantia().getId());
        ps.setString(5, producto.getId());

        return ps;
    }

    @Override
    public Producto map(ResultSet rs) throws SQLException {
        Producto producto = null;
        if (rs.first()) {
            producto = new Producto();
            producto.setId(rs.getString("producto_id"));
            producto.setNombre(rs.getString("nombre"));
            producto.setDescripcion(rs.getString("descripcion"));
            producto.fromImagesTokenized(rs.getString("imagenes"));
            producto.setGarantia(GarantiaDaoImpl.getInstance().findById(rs.getString("garantia_id")));
        }
        return producto;
    }

    @Override
    public PreparedStatement findBy(String field, String value, Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Find by is not supported on class Producto!");
    }
}
