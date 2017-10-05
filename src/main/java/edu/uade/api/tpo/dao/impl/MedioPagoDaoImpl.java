package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractDao;
import edu.uade.api.tpo.model.MedioPago;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedioPagoDaoImpl extends AbstractDao<MedioPago> {

    private static MedioPagoDaoImpl instance;

    private MedioPagoDaoImpl() {

    }

    public static MedioPagoDaoImpl getInstance() {
        if (instance == null) {
            instance = new MedioPagoDaoImpl();
        }
        return instance;
    }

    @Override
    public PreparedStatement findById(String id, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".medios_pago WHERE medio_pago_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, id);

        return ps;
    }

    @Override
    public PreparedStatement findBy(String field, String value, Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Find by is not supported on class MedioPago!");
    }

    @Override
    public PreparedStatement create(MedioPago medioPago, Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Create is not supported on class MedioPago!");
    }

    @Override
    public PreparedStatement update(MedioPago medioPago, Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Update is not supported on class MedioPago!");
    }

    @Override
    public MedioPago map(ResultSet rs) throws SQLException {
        MedioPago medioPago = null;
        if (rs.first()) {
            medioPago = MedioPago.valueOf(rs.getString("medio_pago_id"));
        }
        return medioPago;
    }

    public List<MedioPago> findByPublicacionId(String publicacionId) throws SQLException {
        try (Connection conn = this.getConnection(); PreparedStatement ps = getStatementByPublicacionId(publicacionId, conn); ResultSet rs = ps.executeQuery()) {
            List<MedioPago> mediosPago = mapList(rs);
            return mediosPago;
        }
    }

    public List<MedioPago> findBySubastaId(String subastaId) throws SQLException {
        try (Connection conn = this.getConnection(); PreparedStatement ps = getStatementBySubastaId(subastaId, conn); ResultSet rs = ps.executeQuery()) {
            List<MedioPago> mediosPago = mapList(rs);
            return mediosPago;
        }
    }

    private PreparedStatement getStatementByPublicacionId(String publicacionId, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".publicaciones_medios_pago WHERE publicacion_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, publicacionId);
        return ps;
    }

    private PreparedStatement getStatementBySubastaId(String subastaId, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + schema + ".subastas_medios_pago WHERE subasta_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, subastaId);
        return ps;
    }

    private List<MedioPago> mapList(ResultSet rs) throws SQLException {
        List<MedioPago> mediosPago = new ArrayList<>();
        while (rs.next()) {
            mediosPago.add(MedioPago.valueOf(rs.getString("medio_pago_id")));
        }
        return mediosPago;
    }

    public void addMedioPagoToPublicacion(String publicacionId, MedioPago medioPago) throws SQLException {
        try (Connection conn = this.getConnection(); PreparedStatement ps = getStatementAddMedioPagoToPublicacion(publicacionId, medioPago, conn)) {
            ps.execute();
        }
    }

    private PreparedStatement getStatementAddMedioPagoToPublicacion(String publicacionId, MedioPago medioPago, Connection conn) throws SQLException {
        String query = "INSERT INTO " + schema + ".publicaciones_medios_pago VALUES (?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, publicacionId);
        ps.setString(2, medioPago.getId());
        return ps;
    }

    public void addMedioPagoToSubasta(String subastaId, MedioPago medioPago) throws SQLException {
        try (Connection conn = this.getConnection(); PreparedStatement ps = getStatementAddMedioPagoToSubasta(subastaId, medioPago, conn)) {
            ps.execute();
        }
    }

    private PreparedStatement getStatementAddMedioPagoToSubasta(String subastaId, MedioPago medioPago, Connection conn) throws SQLException {
        String query = "INSERT INTO " + schema + ".subastas_medios_pago VALUES (?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, subastaId);
        ps.setString(2, medioPago.getId());
        return ps;
    }

    public void updateMediosPagoToPublicacion(String publicacionId, List<MedioPago> mediosPagos) throws SQLException {
        try (Connection conn = this.getConnection(); PreparedStatement ps = getDeleteStatementByPublicacion(publicacionId, conn)) {
            ps.execute();
        }
        for (MedioPago medioPago : mediosPagos) {
            this.addMedioPagoToPublicacion(publicacionId, medioPago);
        }
    }

    private PreparedStatement getDeleteStatementByPublicacion(String publicacionId, Connection conn) throws SQLException {
        String query = "DELETE FROM " + schema + ".publicaciones_medios_pago WHERE publicacion_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, publicacionId);
        return ps;
    }

    public void updateMediosPagoToSubasta(String subastaId, List<MedioPago> mediosPagos) throws SQLException {
        try (Connection conn = this.getConnection(); PreparedStatement ps = getDeleteStatementBySubasta(subastaId, conn)) {
            ps.execute();
        }
        for (MedioPago medioPago : mediosPagos) {
            this.addMedioPagoToSubasta(subastaId, medioPago);
        }
    }

    private PreparedStatement getDeleteStatementBySubasta(String subastaId, Connection conn) throws SQLException {
        String query = "DELETE FROM " + schema + ".subastas_medios_pago WHERE subasta_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, subastaId);
        return ps;
    }
}
