package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractBaseDao;
import edu.uade.api.tpo.dao.ArticuloDao;
import edu.uade.api.tpo.model.Articulo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticuloDaoImpl extends AbstractBaseDao<Articulo> implements ArticuloDao {

    private static ArticuloDao instance;

    private ArticuloDaoImpl() {

    }

    public static ArticuloDao getInstance() {
        if (instance == null) {
            instance = new ArticuloDaoImpl();
        }
        return instance;
    }

    @Override
    public PreparedStatement findById(String id, Connection conn) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement create(Articulo articulo, Connection conn) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement update(Articulo articulo, Connection conn) throws SQLException {
        return null;
    }

    @Override
    public Articulo map(ResultSet rs) throws SQLException {
        return null;
    }
}
