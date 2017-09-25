package edu.uade.api.tpo.dao;

import edu.uade.api.tpo.model.Articulo;

import java.sql.SQLException;

public interface ArticuloDao {

    public void create(Articulo articulo) throws SQLException;

    public Articulo findById(String id) throws SQLException;

    public void update(Articulo articulo) throws SQLException;

}
