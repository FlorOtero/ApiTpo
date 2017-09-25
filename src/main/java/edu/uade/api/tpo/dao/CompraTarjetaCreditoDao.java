package edu.uade.api.tpo.dao;

import edu.uade.api.tpo.model.CompraTarjetaCredito;

import java.sql.SQLException;

public interface CompraTarjetaCreditoDao {

    public void create(CompraTarjetaCredito compra) throws SQLException;

    public CompraTarjetaCredito findById(String id) throws SQLException;

    public void update(CompraTarjetaCredito compra) throws SQLException;
}
