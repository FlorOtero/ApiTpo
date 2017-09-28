package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractDao;
import edu.uade.api.tpo.dao.GenericDao;
import edu.uade.api.tpo.model.CompraTarjetaCredito;
import edu.uade.api.tpo.model.Publicacion;
import edu.uade.api.tpo.util.UUIDUtils;

import java.sql.*;

public class CompraTarjetaCreditoDaoImpl extends AbstractDao<CompraTarjetaCredito> {

    private static GenericDao<CompraTarjetaCredito> instance;

    private CompraTarjetaCreditoDaoImpl() {

    }

    public static GenericDao<CompraTarjetaCredito> getInstance() {
        if (instance == null) {
            instance = new CompraTarjetaCreditoDaoImpl();
        }
        return instance;
    }

    @Override
    public PreparedStatement findById(String id, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".compras_tarjeta_credito WHERE compra_tarjeta_credito_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, id);
        return ps;
    }

    @Override
    public PreparedStatement create(CompraTarjetaCredito compraTarjetaCredito, Connection conn) throws SQLException {
        String query = "INSERT INTO " + schema + ".compras_tarjeta_credito VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, UUIDUtils.generate());
        ps.setString(2, compraTarjetaCredito.getContraparte().getId());
        ps.setString(3, compraTarjetaCredito.getPublicacion().getId());
        ps.setString(4, String.valueOf(compraTarjetaCredito.getEstado()));
        ps.setTimestamp(5, new Timestamp(compraTarjetaCredito.getFecha().getTime()));
        ps.setString(6, compraTarjetaCredito.getEntidad().getId());
        ps.setString(7, compraTarjetaCredito.getNumeroTarjeta());
        return ps;
    }

    @Override
    public PreparedStatement update(CompraTarjetaCredito compraTarjetaCredito, Connection conn) throws SQLException {
        String query = "UPDATE " + schema + ".compras_tarjeta_credito SET contraparte_id = ?, publicacion_id = ?, estado = ?, fecha = ?, entidad_recaudadora_id = ?, numero_tarjeta = ? WHERE compra_tarjeta_credito_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, compraTarjetaCredito.getContraparte().getId());
        ps.setString(2, compraTarjetaCredito.getPublicacion().getId());
        ps.setString(3, String.valueOf(compraTarjetaCredito.getEstado()));
        ps.setTimestamp(4, new Timestamp(compraTarjetaCredito.getFecha().getTime()));
        ps.setString(5, compraTarjetaCredito.getEntidad().getId());
        ps.setString(6, compraTarjetaCredito.getNumeroTarjeta());
        return ps;
    }

    @Override
    public CompraTarjetaCredito map(ResultSet rs) throws SQLException {
        CompraTarjetaCredito compra = null;
        if (rs.first()) {
            compra = new CompraTarjetaCredito();
            compra.setId(rs.getString("compra_tarjeta_credito_id"));
            compra.setContraparte(UsuarioDaoImpl.getInstance().findById(rs.getString("contraparte_id")));
            Publicacion pub = PublicacionDaoImpl.getInstance().findById(rs.getString("publicacion_id"));
            if (pub == null) {
                pub = SubastaDaoImpl.getInstance().findById(rs.getString("publicacion_id"));
            }
            compra.setPublicacion(pub);
            compra.setEstado(rs.getString("estado").charAt(0));
            compra.setFecha(rs.getTimestamp("fecha"));
            compra.setEntidad(EntidadRecaudadoraDaoImpl.getInstance().findById(rs.getString("entidad_recaudadora_id")));
            compra.setNumeroTarjeta(rs.getString("numero_tarjeta"));
        }

        return compra;
    }
}
