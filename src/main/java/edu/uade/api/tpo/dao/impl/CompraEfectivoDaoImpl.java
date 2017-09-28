package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractDao;
import edu.uade.api.tpo.dao.GenericDao;
import edu.uade.api.tpo.model.CompraEfectivo;
import edu.uade.api.tpo.model.Publicacion;
import edu.uade.api.tpo.util.UUIDUtils;

import java.sql.*;

public class CompraEfectivoDaoImpl extends AbstractDao<CompraEfectivo> {

    private static GenericDao<CompraEfectivo> instance;

    private CompraEfectivoDaoImpl() {

    }

    public static GenericDao<CompraEfectivo> getInstance() {
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
        String query = "INSERT INTO " + schema + ".compras_efectivo VALUES(?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, UUIDUtils.generate());
        ps.setString(2, compraEfectivo.getContraparte().getId());
        ps.setString(3, compraEfectivo.getPublicacion().getId());
        ps.setString(4, String.valueOf(compraEfectivo.getEstado()));
        ps.setTimestamp(5, new Timestamp(compraEfectivo.getFecha().getTime()));
        return ps;
    }

    @Override
    public PreparedStatement update(CompraEfectivo compraEfectivo, Connection conn) throws SQLException {
        String query = "UPDATE " + schema + ".compras_efectivo SET contraparte_id = ?, publicacion_id = ?, estado = ?, fecha = ? where compra_efectivo_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, compraEfectivo.getContraparte().getId());
        ps.setString(2, compraEfectivo.getPublicacion().getId());
        ps.setString(3, String.valueOf(compraEfectivo.getEstado()));
        ps.setTimestamp(4, new Timestamp(compraEfectivo.getFecha().getTime()));
        ps.setString(5, compraEfectivo.getId());
        return ps;
    }

    @Override
    public CompraEfectivo map(ResultSet rs) throws SQLException {
        CompraEfectivo compra = null;
        if (rs.first()) {
            compra = new CompraEfectivo();
            compra.setId(rs.getString("compra_efectivo_id"));
            compra.setContraparte(UsuarioDaoImpl.getInstance().findById(rs.getString("contraparte_id")));
            Publicacion pub = PublicacionDaoImpl.getInstance().findById(rs.getString("publicacion_id"));
            if (pub == null) {
                pub = SubastaDaoImpl.getInstance().findById(rs.getString("publicacion_id"));
            }
            compra.setPublicacion(pub);
            compra.setEstado(rs.getString("estado").charAt(0));
            compra.setFecha(rs.getTimestamp("fecha"));
        }
        return compra;
    }
}
