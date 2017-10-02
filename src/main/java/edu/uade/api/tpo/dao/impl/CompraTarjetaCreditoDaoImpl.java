package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractManyToOneDao;
import edu.uade.api.tpo.dao.ManyToOneDao;
import edu.uade.api.tpo.model.CompraTarjetaCredito;
import edu.uade.api.tpo.model.Publicacion;
import edu.uade.api.tpo.util.UUIDUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraTarjetaCreditoDaoImpl extends AbstractManyToOneDao<CompraTarjetaCredito> {

    private static ManyToOneDao<CompraTarjetaCredito> instance;

    private CompraTarjetaCreditoDaoImpl() {

    }

    public static ManyToOneDao<CompraTarjetaCredito> getInstance() {
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
        String query = "INSERT INTO " + schema + ".compras_tarjeta_credito VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, UUIDUtils.generate());
        ps.setString(2, compraTarjetaCredito.getContraparte().getId());
        ps.setString(3, compraTarjetaCredito.getPublicacion().getId());
        ps.setString(4, String.valueOf(compraTarjetaCredito.getEstado()));
        ps.setTimestamp(5, new Timestamp(compraTarjetaCredito.getFecha().getTime()));
        ps.setString(6, compraTarjetaCredito.getEntidad().getId());
        ps.setString(7, compraTarjetaCredito.getNumeroTarjeta());
        ps.setString(8, compraTarjetaCredito.getCuentaCorrienteId());
        return ps;
    }

    @Override
    public PreparedStatement update(CompraTarjetaCredito compraTarjetaCredito, Connection conn) throws SQLException {
        String query = "UPDATE " + schema + ".compras_tarjeta_credito SET contraparte_id = ?, publicacion_id = ?, estado = ?, fecha = ?, entidad_recaudadora_id = ?, numero_tarjeta = ?, cuenta_corriente_id = ? WHERE compra_tarjeta_credito_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, compraTarjetaCredito.getContraparte().getId());
        ps.setString(2, compraTarjetaCredito.getPublicacion().getId());
        ps.setString(3, String.valueOf(compraTarjetaCredito.getEstado()));
        ps.setTimestamp(4, new Timestamp(compraTarjetaCredito.getFecha().getTime()));
        ps.setString(5, compraTarjetaCredito.getEntidad().getId());
        ps.setString(6, compraTarjetaCredito.getNumeroTarjeta());
        ps.setString(7, compraTarjetaCredito.getCuentaCorrienteId());
        return ps;
    }

    @Override
    public CompraTarjetaCredito map(ResultSet rs) throws SQLException {
        CompraTarjetaCredito compra = null;
        if (rs.first()) {
            compra = mapRow(rs);
        }
        return compra;
    }

    @Override
    public PreparedStatement findManyBy(String field, String value, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".compras_tarjeta_credito WHERE " + field + " = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, value);
        return ps;
    }

    @Override
    public List<CompraTarjetaCredito> mapMany(ResultSet rs) throws SQLException {
        List<CompraTarjetaCredito> compras = new ArrayList<>();
        while (rs.next()) {
            compras.add(mapRow(rs));
        }
        return compras;
    }

    private CompraTarjetaCredito mapRow(ResultSet rs) throws SQLException {
        CompraTarjetaCredito compra = new CompraTarjetaCredito();
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
        compra.setCuentaCorrienteId(rs.getString("cuenta_corriente_id"));
        return compra;
    }
}
