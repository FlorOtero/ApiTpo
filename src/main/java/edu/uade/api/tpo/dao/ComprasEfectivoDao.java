package edu.uade.api.tpo.dao;

import edu.uade.api.tpo.model.CompraEfectivo;

import java.sql.SQLException;

public interface ComprasEfectivoDao {

    public void create(CompraEfectivo compra) throws SQLException;

    public CompraEfectivo findById(String id) throws SQLException;

    public void update(CompraEfectivo compra) throws SQLException;

}