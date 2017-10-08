package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractManyToOneDao;
import edu.uade.api.tpo.dao.ManyToOneDao;
import edu.uade.api.tpo.model.Oferta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfertaDaoImpl extends AbstractManyToOneDao<Oferta> {

    private static ManyToOneDao<Oferta> instance;

    private OfertaDaoImpl() {
    }

    public static ManyToOneDao<Oferta> getInstance() {
        if (instance == null) {
            instance = new OfertaDaoImpl();
        }
        return instance;
    }

    @Override
    public PreparedStatement findBy(String field, String value, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".ofertas WHERE " + field + " = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, value);
        return ps;
    }

    @Override
    public PreparedStatement create(Oferta oferta, Connection conn) throws SQLException {
        String query = "INSERT INTO " + schema + ".ofertas VALUES(?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, oferta.getId());
        ps.setString(2, oferta.getUsuario().getId());
        ps.setString(3, oferta.getSubasta().getId());
        ps.setFloat(4, oferta.getMonto());
        ps.setTimestamp(5, new Timestamp(oferta.getFecha().getTime()));
        return ps;
    }

    @Override
    public PreparedStatement update(Oferta oferta, Connection conn) throws SQLException {
        String query = "UPDATE " + schema + ".ofertas SET usuario_id = ?, subasta_id = ?, monto = ?, fecha = ? WHERE oferta_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, oferta.getUsuario().getId());
        ps.setString(2, oferta.getSubasta().getId());
        ps.setFloat(3, oferta.getMonto());
        ps.setTimestamp(4, new Timestamp(oferta.getFecha().getTime()));
        ps.setString(5, oferta.getId());
        return ps;
    }

    @Override
    public Oferta map(ResultSet rs) throws SQLException {
        Oferta oferta = null;
        if (rs.first()) {
            oferta = mapRow(rs);
        }
        return oferta;
    }

    @Override
    public PreparedStatement findById(String id, Connection conn) throws SQLException {
        return this.findBy("subasta_id", id, conn);
    }

    @Override
    public PreparedStatement findManyBy(String field, String value, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".ofertas WHERE " + field + " = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, value);
        return ps;
    }

    @Override
    public List<Oferta> mapMany(ResultSet rs) throws SQLException {
        List<Oferta> ofertas = new ArrayList<>();
        while (rs.next()) {
            ofertas.add(mapRow(rs));
        }

        return ofertas;
    }

    private Oferta mapRow(ResultSet rs) throws SQLException {
        Oferta oferta = new Oferta();
        oferta.setId(rs.getString("oferta_id"));
        oferta.setUsuario(UsuarioDaoImpl.getInstance().findById(rs.getString("usuario_id")));
        oferta.setSubasta(SubastaDaoImpl.getInstance().findById(rs.getString("subasta_id")));
        oferta.setMonto(rs.getFloat("monto"));
        oferta.setFecha(rs.getDate("fecha"));
        return oferta;
    }
    
    @Override
    public void delete(Oferta t) throws SQLException {
    	throw new UnsupportedOperationException("Delete is not supported on class Oferta!");
    }
}
