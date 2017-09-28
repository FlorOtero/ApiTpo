package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractDao;
import edu.uade.api.tpo.dao.GenericDao;
import edu.uade.api.tpo.model.Reputacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReputacionDaoImpl extends AbstractDao<Reputacion> {

    private static GenericDao<Reputacion> instance;

    private ReputacionDaoImpl() {

    }

    public static GenericDao<Reputacion> getInstance() {
        if (instance == null) {
            instance = new ReputacionDaoImpl();
        }
        return instance;
    }

    @Override
    public PreparedStatement findById(String id, Connection conn) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement create(Reputacion reputacion, Connection conn) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement update(Reputacion reputacion, Connection conn) throws SQLException {
        return null;
    }

    @Override
    public Reputacion map(ResultSet rs) throws SQLException {
        return null;
    }
}
