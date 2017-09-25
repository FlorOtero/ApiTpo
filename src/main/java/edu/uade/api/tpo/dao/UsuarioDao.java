package edu.uade.api.tpo.dao;

import edu.uade.api.tpo.model.Usuario;

import java.sql.SQLException;

public interface UsuarioDao {

    public void create(Usuario usuario) throws SQLException;

    public Usuario findById(String id) throws SQLException;

    public void update(Usuario usuario) throws SQLException;

}
