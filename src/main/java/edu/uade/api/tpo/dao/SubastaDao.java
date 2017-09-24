package edu.uade.api.tpo.dao;

import edu.uade.api.tpo.model.Subasta;

import java.sql.SQLException;

public interface SubastaDao {

    public void create(Subasta publicacion) throws SQLException;

    public Subasta findById(String id) throws SQLException;

    public void update(Subasta publicacion) throws SQLException;
}
