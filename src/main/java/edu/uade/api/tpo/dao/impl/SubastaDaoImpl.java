package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractManyToOneDao;
import edu.uade.api.tpo.model.Estado;
import edu.uade.api.tpo.model.MedioPago;
import edu.uade.api.tpo.model.Subasta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubastaDaoImpl extends AbstractManyToOneDao<Subasta> {


    private static final Logger logger = LoggerFactory.getLogger(SubastaDaoImpl.class);
    private static SubastaDaoImpl instance;

    private SubastaDaoImpl() {

    }

    public static SubastaDaoImpl getInstance() {
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
        ArticuloDaoImpl.getInstance().create(subasta.getArticulo());

        String query = "INSERT INTO " + schema + ".subastas VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, subasta.getId());
        ps.setString(2, subasta.getUsuarioId());
        ps.setTimestamp(3, new Timestamp(subasta.getFechaDesde().getTime()));
        ps.setString(4, String.valueOf(subasta.getEstado()));
        ps.setString(5, subasta.getArticulo().getId());
        ps.setFloat(6, subasta.getPrecioMin());
        ps.setInt(7, subasta.getDiasVigencia());
        ps.setFloat(8, subasta.getPrecioInicial());
        return ps;
    }

    @Override
    public void doAfterCreate(Subasta subasta) throws SQLException {
        if (subasta.getMediosPago() != null && !subasta.getMediosPago().isEmpty()) {
            for (MedioPago medioPago : subasta.getMediosPago()) {
                MedioPagoDaoImpl.getInstance().addMedioPagoToSubasta(subasta.getId(), medioPago);
            }
        }
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
        ArticuloDaoImpl.getInstance().update(subasta.getArticulo());

        String query = "UPDATE " + schema
                + ".subastas SET usuario_id = ?, fecha_desde = ?, estado = ?, articulo_id = ?, precio_min = ?, dias_vigencia = ?, precio_inicial = ? where subasta_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, subasta.getUsuarioId());
        ps.setTimestamp(2, new Timestamp(subasta.getFechaDesde().getTime()));
        ps.setString(3, String.valueOf(subasta.getEstado()));
        ps.setString(4, subasta.getArticulo().getId());
        ps.setFloat(5, subasta.getPrecioMin());
        ps.setInt(6, subasta.getDiasVigencia());
        ps.setFloat(7, subasta.getPrecioInicial());
        ps.setString(8, subasta.getId());
        if (subasta.getMediosPago() != null && !subasta.getMediosPago().isEmpty()) {
            MedioPagoDaoImpl.getInstance().updateMediosPagoToSubasta(subasta.getId(), subasta.getMediosPago());
        }
        if (subasta.getOfertas() != null) {
            subasta.getOfertas().stream().filter(o -> o.getId() == null).collect(Collectors.toList()).forEach(oferta -> {
                try {
                    OfertaDaoImpl.getInstance().create(oferta);
                } catch (SQLException e) {
                    logger.error("Error agregando ofertas", e);
                }
            });
        }
        return ps;
    }

    @Override
    public PreparedStatement findManyBy(String field, String value, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".subastas WHERE " + field + " = ? AND estado = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, value);
        ps.setString(2, Estado.A.toString());
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
        subasta.setEstado(Estado.valueOf(rs.getString("estado")));
        subasta.setArticulo(ArticuloDaoImpl.getInstance().findById(rs.getString("articulo_id")));
        subasta.setPrecioMin(rs.getFloat("precio_min"));
        subasta.setDiasVigencia(rs.getInt("dias_vigencia"));
        subasta.setPrecioInicial(rs.getFloat("precio_inicial"));
        subasta.setMediosPago(MedioPagoDaoImpl.getInstance().findBySubastaId(subasta.getId()));
        subasta.setOfertas(OfertaDaoImpl.getInstance().findManyBy("subasta_id", subasta.getId()));
        return subasta;
    }

    @Override
    public PreparedStatement findBy(String field, String value, Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Find by is not supported on class Subasta!");
    }

    @Override
    public PreparedStatement findManyLike(String field, String value, Connection conn) throws SQLException {
        String query = "SELECT subasta_id, usuario_id, fecha_desde, precio_min, dias_vigencia, precio_inicial, estado, articulo_id, producto_id, nombre, descripcion FROM " + schema + ".subastas AS subasta, " + schema + ".productos AS producto " +
                "WHERE lower(producto." + field + ") LIKE ? AND subasta.articulo_id = producto.producto_id AND subasta.estado = ? " +
                "UNION ALL " +
                "SELECT subasta_id, usuario_id, fecha_desde, precio_min, dias_vigencia, precio_inicial, estado, articulo_id, servicio_id, nombre, descripcion FROM " + schema + ".subastas AS subasta, " + schema + ".servicios AS servicio " +
                "WHERE lower(servicio." + field + ") LIKE ? AND subasta.articulo_id = servicio.servicio_id AND subasta.estado = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        String status = Estado.A.toString();
        String valueLike = "%" + value + "%";
        ps.setString(1, valueLike);
        ps.setString(2, status);
        ps.setString(3, valueLike);
        ps.setString(4, status);
        return ps;
    }

    @Override
    public void delete(Subasta subasta) throws SQLException {
        throw new UnsupportedOperationException("Delete is not supported on Subasta");
    }

    public List<Subasta> findAll() throws SQLException {
        try (Connection conn = this.getConnection(); PreparedStatement ps = getFindAllStatement(conn); ResultSet rs = ps.executeQuery()) {
            return mapMany(rs);
        }
    }

    private PreparedStatement getFindAllStatement(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + schema + ".subastas WHERE estado = ?");
        ps.setString(1, Estado.A.toString());
        return ps;
    }
}
