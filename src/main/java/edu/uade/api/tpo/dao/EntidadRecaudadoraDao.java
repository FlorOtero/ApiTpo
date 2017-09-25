package edu.uade.api.tpo.dao;

import edu.uade.api.tpo.model.EntidadRecaudadora;

import java.sql.SQLException;

public interface EntidadRecaudadoraDao {

    public EntidadRecaudadora findById(String id) throws SQLException;

}
