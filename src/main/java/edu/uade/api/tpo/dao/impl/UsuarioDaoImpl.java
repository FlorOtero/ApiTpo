package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractBaseDao;
import edu.uade.api.tpo.dao.UsuarioDao;
import edu.uade.api.tpo.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDaoImpl extends AbstractBaseDao<Usuario> implements UsuarioDao {

    private static UsuarioDao instance;

    private UsuarioDaoImpl() {
    }

    public static UsuarioDao getInstance() {
        if (instance == null) {
            instance = new UsuarioDaoImpl();
        }
        return instance;
    }

    @Override
    public PreparedStatement findById(String id, Connection conn) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement create(Usuario usuario, Connection conn) throws SQLException {
        return null;
    }

    @Override
    public Usuario map(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement update(Usuario usuario, Connection conn) throws SQLException {
        return null;
    }
}
