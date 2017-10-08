package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractDao;
import edu.uade.api.tpo.dao.GenericDao;
import edu.uade.api.tpo.model.Password;
import edu.uade.api.tpo.util.Encriptacion;
import edu.uade.api.tpo.util.UUIDUtils;

import java.sql.*;
public class PasswordDaoImpl extends AbstractDao<Password> {

    private static GenericDao<Password> instance;

    private PasswordDaoImpl() {

    }

    public static GenericDao<Password> getInstance() {
        if (instance == null) {
            instance = new PasswordDaoImpl();
        }
        return instance;
    }

    @Override
    public PreparedStatement findById(String id, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".passwords WHERE password_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, id);
        return ps;
    }

    @Override
    public PreparedStatement create(Password password, Connection conn) throws SQLException {
    	String query = "INSERT INTO " + schema + ".passwords VALUES(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, password.getId());
        ps.setString(2, password.getValor());
        ps.setTimestamp(3, new Timestamp(password.getFechaModificacion().getTime()));
        return ps;
    }

    @Override
    public PreparedStatement update(Password password, Connection conn) throws SQLException {
        String query = "UPDATE " + schema + ".passwords SET valor = ?, fecha_modificacion = ? WHERE password_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, password.getValor());
        ps.setTimestamp(2, new Timestamp(password.getFechaModificacion().getTime()));
        ps.setString(3, password.getId());
        return null;
    }

    @Override
    public Password map(ResultSet rs) throws SQLException {
        Password password = null;
        if (rs.first()) {
            password = new Password();
            password.setId(rs.getString("password_id"));
            password.setValor(rs.getString("valor"));
            password.setFechaModificacion(rs.getTimestamp("fecha_modificacion"));
        }
        return password;
    }

    @Override
    public PreparedStatement findBy(String field, String value, Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Find by is not supported on class Password!");
    }
    
    
}
