package edu.uade.api.tpo.dao;

import edu.uade.api.tpo.model.Publicacion;

import java.sql.SQLException;

public interface PublicacionDao {

    public void create(Publicacion publicacion) throws SQLException;

    public Publicacion findById(String id) throws SQLException;

    public void update(Publicacion publicacion) throws SQLException;

}
