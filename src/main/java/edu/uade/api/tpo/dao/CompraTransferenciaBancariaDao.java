package edu.uade.api.tpo.dao;

import edu.uade.api.tpo.model.CompraTransferenciaBancaria;

import java.sql.SQLException;

public interface CompraTransferenciaBancariaDao {

    public void create(CompraTransferenciaBancaria compra) throws SQLException;

    public CompraTransferenciaBancaria findById(String id) throws SQLException;

    public void update(CompraTransferenciaBancaria compra) throws SQLException;
}
