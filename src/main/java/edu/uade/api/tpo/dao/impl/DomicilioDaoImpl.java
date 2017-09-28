package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractDao;
import edu.uade.api.tpo.dao.GenericDao;
import edu.uade.api.tpo.model.Domicilio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DomicilioDaoImpl extends AbstractDao<Domicilio> {

    private static GenericDao<Domicilio> instance;

    private DomicilioDaoImpl() {

    }

    public static GenericDao<Domicilio> getInstance() {
        if (instance == null) {
            instance = new DomicilioDaoImpl();
        }
        return instance;
    }

    @Override
    public PreparedStatement findById(String id, Connection conn) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement create(Domicilio domicilio, Connection conn) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement update(Domicilio domicilio, Connection conn) throws SQLException {
        return null;
    }

    @Override
    public Domicilio map(ResultSet rs) throws SQLException {
        return null;
    }
}
