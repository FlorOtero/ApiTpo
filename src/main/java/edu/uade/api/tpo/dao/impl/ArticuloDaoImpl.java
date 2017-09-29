package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractDao;
import edu.uade.api.tpo.dao.GenericDao;
import edu.uade.api.tpo.model.Articulo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticuloDaoImpl extends AbstractDao<Articulo> {

    private static GenericDao<Articulo> instance;

    private ArticuloDaoImpl() {

    }

    public static GenericDao<Articulo> getInstance() {
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
