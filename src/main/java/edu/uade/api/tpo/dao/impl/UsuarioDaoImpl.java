package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractGenericDao;
import edu.uade.api.tpo.dao.GenericDao;
import edu.uade.api.tpo.model.Usuario;
import edu.uade.api.tpo.util.UUIDUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDaoImpl extends AbstractGenericDao<Usuario> {

    private static GenericDao<Usuario> instance;

    private UsuarioDaoImpl() {
    }

    public static GenericDao<Usuario> getInstance() {
        if (instance == null) {
            instance = new UsuarioDaoImpl();
        }
        return instance;
    }

    @Override
    public PreparedStatement findById(String id, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".usuarios WHERE usuario_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, id);
        return ps;
    }

    @Override
    public PreparedStatement create(Usuario usuario, Connection conn) throws SQLException {
        String query = "INSERT INTO " + schema + ".usuarios VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, UUIDUtils.generate());
        ps.setString(2, usuario.getNombreUsuario());
        ps.setString(3, usuario.getNombre());
        ps.setString(4, usuario.getApellido());
        ps.setString(5, usuario.getDomicilio().getId());
        ps.setString(6, usuario.getMail());
        ps.setString(7, usuario.getPassword().getId());
        ps.setString(8, usuario.getCuentaCorriente().getId());
        ps.setString(9, usuario.getReputacion().getId());
        return ps;
    }

    @Override
    public Usuario map(ResultSet rs) throws SQLException {
        Usuario usuario = null;
        if(rs.first()) {
            usuario = new Usuario();
            usuario.setId(rs.getString("usuario_id"));
            usuario.setNombreUsuario(rs.getString("nombre_usuario"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setApellido(rs.getString("apellido"));
        }

        return usuario;
    }

    @Override
    public PreparedStatement update(Usuario usuario, Connection conn) throws SQLException {
        return null;
    }
}
