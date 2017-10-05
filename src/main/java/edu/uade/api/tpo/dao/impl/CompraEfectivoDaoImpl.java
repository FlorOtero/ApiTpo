package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractManyToOneDao;
import edu.uade.api.tpo.dao.ManyToOneDao;
import edu.uade.api.tpo.model.CompraEfectivo;
import edu.uade.api.tpo.model.Publicacion;
import edu.uade.api.tpo.util.UUIDUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraEfectivoDaoImpl extends AbstractManyToOneDao<CompraEfectivo> {

    private static ManyToOneDao<CompraEfectivo> instance;

    private CompraEfectivoDaoImpl() {

    }

    public static ManyToOneDao<CompraEfectivo> getInstance() {
        if (instance == null) {
            instance = new CompraEfectivoDaoImpl();
        }
        return instance;
    }

    @Override
    public PreparedStatement findById(String id, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".compras_efectivo WHERE compra_efectivo_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, id);
        return ps;
    }

    @Override
    public PreparedStatement create(CompraEfectivo compraEfectivo, Connection conn) throws SQLException {
        String query = "INSERT INTO " + schema + ".compras_efectivo VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, compraEfectivo.getId());
        ps.setString(2, compraEfectivo.getContraparte().getId());
        ps.setString(3, compraEfectivo.getPublicacion().getId());
        ps.setString(4, String.valueOf(compraEfectivo.getEstado()));
        ps.setTimestamp(5, new Timestamp(compraEfectivo.getFecha().getTime()));
        ps.setString(6, compraEfectivo.getCuentaCorrienteId());
        return ps;
    }

    @Override
    public PreparedStatement update(CompraEfectivo compraEfectivo, Connection conn) throws SQLException {
        String query = "UPDATE " + schema + ".compras_efectivo SET contraparte_id = ?, publicacion_id = ?, estado = ?, fecha = ?, cuenta_corriente_id = ? where compra_efectivo_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, compraEfectivo.getContraparte().getId());
        ps.setString(2, compraEfectivo.getPublicacion().getId());
        ps.setString(3, String.valueOf(compraEfectivo.getEstado()));
        ps.setTimestamp(4, new Timestamp(compraEfectivo.getFecha().getTime()));
        ps.setString(5, compraEfectivo.getId());
        ps.setString(6, compraEfectivo.getCuentaCorrienteId());
        return ps;
    }

    @Override
    public CompraEfectivo map(ResultSet rs) throws SQLException {
        CompraEfectivo compra = null;
        if (rs.first()) {
            compra = mapRow(rs);
        }
        return compra;
    }

    @Override
    public PreparedStatement findManyBy(String field, String value, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".compras_efectivo WHERE " + field + " = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, value);
        return ps;
    }

    @Override
    public List<CompraEfectivo> mapMany(ResultSet rs) throws SQLException {
        List<CompraEfectivo> compras = new ArrayList<>();
        while (rs.next()) {
            compras.add(mapRow(rs));
        }
        return compras;
    }

    private CompraEfectivo mapRow(ResultSet rs) throws SQLException {
        CompraEfectivo compra = new CompraEfectivo();
        compra.setId(rs.getString("compra_efectivo_id"));
        compra.setContraparte(UsuarioDaoImpl.getInstance().findById(rs.getString("contraparte_id")));
        Publicacion pub = PublicacionDaoImpl.getInstance().findById(rs.getString("publicacion_id"));
        if (pub == null) {
            pub = SubastaDaoImpl.getInstance().findById(rs.getString("publicacion_id"));
        }
        compra.setPublicacion(pub);
        compra.setEstado(rs.getString("estado").charAt(0));
        compra.setFecha(rs.getTimestamp("fecha"));
        compra.setCuentaCorrienteId(rs.getString("cuenta_corriente_id"));

        return compra;
    }

    @Override
    public PreparedStatement findBy(String field, String value, Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Find by is not supported on class CompraEfectivo!");
    }
}
