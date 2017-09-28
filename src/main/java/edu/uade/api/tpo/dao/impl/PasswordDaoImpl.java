package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractDao;
import edu.uade.api.tpo.dao.GenericDao;
import edu.uade.api.tpo.model.Password;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        return null;
    }

    @Override
    public PreparedStatement create(Password password, Connection conn) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement update(Password password, Connection conn) throws SQLException {
        return null;
    }

    @Override
    public Password map(ResultSet rs) throws SQLException {
        return null;
    }
}
