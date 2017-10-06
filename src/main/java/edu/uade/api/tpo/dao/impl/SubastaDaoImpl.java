package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractManyToOneDao;
import edu.uade.api.tpo.dao.ManyToOneDao;
import edu.uade.api.tpo.model.Subasta;
import edu.uade.api.tpo.util.UUIDUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubastaDaoImpl extends AbstractManyToOneDao<Subasta> {

    private static ManyToOneDao<Subasta> instance;

    private SubastaDaoImpl() {

    }

    public static ManyToOneDao<Subasta> getInstance() {
        if (instance == null) {
            instance = new SubastaDaoImpl();
        }
        return instance;
    }

    @Override
    public PreparedStatement findById(String id, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".subastas WHERE subasta_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, id);
        return ps;
    }

    @Override
    public PreparedStatement create(Subasta subasta, Connection conn) throws SQLException {
        String query = "INSERT INTO " + schema + ".subastas VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, UUIDUtils.generate());
        ps.setString(2, subasta.getUsuarioId());
        ps.setTimestamp(3, new Timestamp(subasta.getFechaDesde().getTime()));
        ps.setTimestamp(4, new Timestamp(subasta.getFechaHasta().getTime()));
        ps.setFloat(5, subasta.getPrecio());
        ps.setString(6, String.valueOf(subasta.getEstado()));
        ps.setString(7, subasta.getArticulo().getId());
        ps.setFloat(8, subasta.getComision());
        ps.setFloat(9, subasta.getPrecioMin());
        ps.setInt(10, subasta.getDiasVigencia());
        ps.setFloat(11, subasta.getPrecioInicial());
        return ps;
    }

    @Override
    public Subasta map(ResultSet rs) throws SQLException {
        Subasta subasta = null;
        if (rs.first()) {
            subasta = mapRow(rs);
        }

        return subasta;
    }

    @Override
    public PreparedStatement update(Subasta subasta, Connection conn) throws SQLException {
        String query = "UPDATE " + schema + ".subastas SET usuario_id = ?, fecha_desde = ?, fecha_hasta = ?, precio = ?, estado = ?, articulo_id = ?, comision = ?, precio_min = ?, dias_vigencia = ?, precio_inicial = ? where subasta_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, subasta.getUsuarioId());
        ps.setTimestamp(2, new Timestamp(subasta.getFechaDesde().getTime()));
        ps.setTimestamp(3, new Timestamp(subasta.getFechaHasta().getTime()));
        ps.setFloat(4, subasta.getPrecio());
        ps.setString(5, String.valueOf(subasta.getEstado()));
        ps.setString(6, subasta.getArticulo().getId());
        ps.setFloat(7, subasta.getComision());
        ps.setFloat(8, subasta.getPrecioMin());
        ps.setInt(9, subasta.getDiasVigencia());
        ps.setFloat(10, subasta.getPrecioInicial());
        ps.setString(11, subasta.getId());

        return ps;
    }

    @Override
    public PreparedStatement findManyBy(String field, String value, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".subastas WHERE " + field + " = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, value);
        return ps;
    }

    @Override
    public List<Subasta> mapMany(ResultSet rs) throws SQLException {
        List<Subasta> subastas = new ArrayList<>();
        while (rs.next()) {
            subastas.add(mapRow(rs));
        }
        return subastas;
    }

    private Subasta mapRow(ResultSet rs) throws SQLException {
        Subasta subasta = new Subasta();
        subasta.setId(rs.getString("subasta_id"));
        subasta.setUsuarioId(rs.getString("usuario_id"));
        subasta.setFechaDesde(rs.getTimestamp("fecha_desde"));
        subasta.setFechaHasta(rs.getTimestamp("fecha_hasta"));
        subasta.setPrecio(rs.getFloat("precio"));
        subasta.setEstado(rs.getString("estado").charAt(0));
        subasta.setArticulo(ArticuloDaoImpl.getInstance().findById(rs.getString("articulo_id")));
        subasta.setComision(rs.getFloat("comision"));
        subasta.setPrecioMin(rs.getFloat("precio_min"));
        subasta.setDiasVigencia(rs.getInt("dias_vigencia"));
        subasta.setPrecioInicial(rs.getFloat("precio_inicial"));
        return subasta;
    }

    @Override
    public PreparedStatement findBy(String field, String value, Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Find by is not supported on class Subasta!");
    }
}
