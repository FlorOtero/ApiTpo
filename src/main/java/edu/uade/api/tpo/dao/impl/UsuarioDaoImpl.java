package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractDao;
import edu.uade.api.tpo.dao.GenericDao;
import edu.uade.api.tpo.model.Estado;
import edu.uade.api.tpo.model.Publicacion;
import edu.uade.api.tpo.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UsuarioDaoImpl extends AbstractDao<Usuario> {

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
        String query = "SELECT * FROM " + schema + ".usuarios WHERE usuario_id = ? AND estado = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, id);
        ps.setString(2, Estado.A.toString());
        return ps;
    }

    @Override
    public PreparedStatement findBy(String field, String value, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".usuarios WHERE " + field + " = ? AND estado = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, value);
        ps.setString(2, Estado.A.toString());
        return ps;
    }

    @Override
    public PreparedStatement create(Usuario usuario, Connection conn) throws SQLException {
        //Cascade domicilio & password before create user
        DomicilioDaoImpl.getInstance().create(usuario.getDomicilio());
        PasswordDaoImpl.getInstance().create(usuario.getPassword());
        CuentaCorrienteDaoImpl.getInstance().create(usuario.getCuentaCorriente());

        String query = "INSERT INTO " + schema + ".usuarios VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, usuario.getId());
        ps.setString(2, usuario.getNombreUsuario());
        ps.setString(3, usuario.getNombre());
        ps.setString(4, usuario.getApellido());
        ps.setString(5, usuario.getDomicilio().getId());
        ps.setString(6, usuario.getMail());
        ps.setString(7, usuario.getPassword().getId());
        ps.setString(8, usuario.getCuentaCorriente().getId());
        ps.setString(9, usuario.getEstado().getVal());
        return ps;
    }

    @Override
    public Usuario map(ResultSet rs) throws SQLException {
        Usuario usuario = null;
        if (rs.first()) {
            usuario = new Usuario();
            usuario.setId(rs.getString("usuario_id"));
            usuario.setNombreUsuario(rs.getString("nombre_usuario"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setApellido(rs.getString("apellido"));
            usuario.setDomicilio(DomicilioDaoImpl.getInstance().findById(rs.getString("domicilio_id")));
            usuario.setMail(rs.getString("mail"));
            usuario.setPassword(PasswordDaoImpl.getInstance().findById(rs.getString("password_id")));
            usuario.setCuentaCorriente(CuentaCorrienteDaoImpl.getInstance().findById(rs.getString("cuenta_corriente_id")));
            usuario.setCalificaciones(CalificacionDaoImpl.getInstance().findManyBy("usuario_id", usuario.getId()));
            List<Publicacion> publicaciones = new ArrayList<>();
            publicaciones.addAll(Collections.unmodifiableList(PublicacionDaoImpl.getInstance().findManyBy("usuario_id", usuario.getId())));
            publicaciones.addAll(Collections.unmodifiableList(SubastaDaoImpl.getInstance().findManyBy("usuario_id", usuario.getId())));
            usuario.setPublicaciones(publicaciones);
            usuario.setEstado(Estado.valueOf(rs.getString("estado")));
        }

        return usuario;
    }

    @Override
    public PreparedStatement update(Usuario usuario, Connection conn) throws SQLException {
        String query = "UPDATE " + schema + ".usuarios SET nombre_usuario = ?, nombre = ?, apellido = ?, domicilio_id = ?, mail = ?, password_id = ?, cuenta_corriente_id = ?, estado = ? WHERE usuario_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, usuario.getNombreUsuario());
        ps.setString(2, usuario.getNombre());
        ps.setString(3, usuario.getApellido());
        ps.setString(4, usuario.getDomicilio().getId());
        ps.setString(5, usuario.getMail());
        ps.setString(6, usuario.getPassword().getId());
        ps.setString(7, usuario.getCuentaCorriente().getId());
        ps.setString(8, usuario.getEstado().toString());
        ps.setString(9, usuario.getId());

        return ps;
    }
}
