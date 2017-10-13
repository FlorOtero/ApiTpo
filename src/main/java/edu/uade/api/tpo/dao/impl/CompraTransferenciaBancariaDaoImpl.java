package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractManyToOneDao;
import edu.uade.api.tpo.dao.ManyToOneDao;
import edu.uade.api.tpo.model.CompraTransferenciaBancaria;
import edu.uade.api.tpo.model.EstadoTransaccion;
import edu.uade.api.tpo.model.Publicacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraTransferenciaBancariaDaoImpl extends AbstractManyToOneDao<CompraTransferenciaBancaria> {

    private static ManyToOneDao<CompraTransferenciaBancaria> instance;

    private CompraTransferenciaBancariaDaoImpl() {

    }

    public static ManyToOneDao<CompraTransferenciaBancaria> getInstance() {
        if (instance == null) {
            instance = new CompraTransferenciaBancariaDaoImpl();
        }
        return instance;
    }

    @Override
    public PreparedStatement findById(String id, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".compras_transf_bancaria WHERE compra_transf_bancaria_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, id);
        return ps;
    }

    @Override
    public PreparedStatement create(CompraTransferenciaBancaria compraTransferenciaBancaria, Connection conn)
            throws SQLException {
        if (compraTransferenciaBancaria.getComision() != null) {
            ComisionDaoImpl.getInstance().create(compraTransferenciaBancaria.getComision());
        }
        String query = "INSERT INTO " + schema + ".compras_transf_bancaria VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, compraTransferenciaBancaria.getId());
        ps.setString(2, compraTransferenciaBancaria.getContraparteId());
        ps.setString(3, compraTransferenciaBancaria.getPublicacion().getId());
        ps.setString(4, String.valueOf(compraTransferenciaBancaria.getEstado()));
        ps.setTimestamp(5, new Timestamp(compraTransferenciaBancaria.getFecha().getTime()));
        ps.setString(6, compraTransferenciaBancaria.getNumeroCta());
        ps.setString(7, compraTransferenciaBancaria.getCuentaCorrienteId());
        ps.setString(8, compraTransferenciaBancaria.getComision() == null ? null : compraTransferenciaBancaria.getComision().getId());

        return ps;
    }

    @Override
    public PreparedStatement update(CompraTransferenciaBancaria compraTransferenciaBancaria, Connection conn)
            throws SQLException {
        if (compraTransferenciaBancaria.getComision() != null) {
            ComisionDaoImpl.getInstance().update(compraTransferenciaBancaria.getComision());
        }
        String query = "UPDATE " + schema
                + ".compras_transf_bancaria SET contraparte_id = ?, publicacion_id = ?, estado = ?, fecha = ?, numero_cuenta = ?, cuenta_corriente_id = ? where compra_transf_bancaria_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, compraTransferenciaBancaria.getContraparteId());
        ps.setString(2, compraTransferenciaBancaria.getPublicacion().getId());
        ps.setString(3, String.valueOf(compraTransferenciaBancaria.getEstado()));
        ps.setTimestamp(4, new Timestamp(compraTransferenciaBancaria.getFecha().getTime()));
        ps.setString(5, compraTransferenciaBancaria.getNumeroCta());
        ps.setString(6, compraTransferenciaBancaria.getId());
        ps.setString(7, compraTransferenciaBancaria.getCuentaCorrienteId());
        ps.setString(8, compraTransferenciaBancaria.getId());
        return ps;
    }

    @Override
    public CompraTransferenciaBancaria map(ResultSet rs) throws SQLException {
        CompraTransferenciaBancaria compra = null;
        if (rs.first()) {
            compra = mapRow(rs);
        }
        return compra;
    }

    @Override
    public PreparedStatement findManyBy(String field, String value, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".compras_transf_bancaria WHERE " + field + " = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, value);
        return ps;
    }

    @Override
    public List<CompraTransferenciaBancaria> mapMany(ResultSet rs) throws SQLException {
        List<CompraTransferenciaBancaria> compras = new ArrayList<>();
        while (rs.next()) {
            compras.add(mapRow(rs));
        }
        return compras;
    }

    private CompraTransferenciaBancaria mapRow(ResultSet rs) throws SQLException {
        CompraTransferenciaBancaria compra = new CompraTransferenciaBancaria();
        compra.setId(rs.getString("compra_transf_bancaria_id"));
        compra.setContraparteId(rs.getString("contraparte_id"));
        Publicacion pub = PublicacionDaoImpl.getInstance().findById(rs.getString("publicacion_id"));
        if (pub == null) {
            pub = SubastaDaoImpl.getInstance().findById(rs.getString("publicacion_id"));
        }
        compra.setPublicacion(pub);
        compra.setEstado(EstadoTransaccion.valueOf(rs.getString("estado")));
        compra.setFecha(rs.getTimestamp("fecha"));
        compra.setNumeroCta(rs.getString("numero_cuenta"));
        compra.setCuentaCorrienteId(rs.getString("cuenta_corriente_id"));
        String comisionId = rs.getString("comision_id");
        if (comisionId != null && !comisionId.isEmpty()) {
            compra.setComision(ComisionDaoImpl.getInstance().findById(comisionId));
        }
        return compra;
    }

    @Override
    public PreparedStatement findBy(String field, String value, Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Find by is not supported on class CompraTransferenciaBancaria!");
    }

    @Override
    public void delete(CompraTransferenciaBancaria t) throws SQLException {
        throw new UnsupportedOperationException("Delete is not supported on class CompraTransferenciaBancaria!");
    }

    @Override
    public PreparedStatement findManyLike(String field, String value, Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Delete is not supported on class CompraTransferenciaBancaria!");
    }
}
