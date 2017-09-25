package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractBaseDao;
import edu.uade.api.tpo.dao.CompraTransferenciaBancariaDao;
import edu.uade.api.tpo.model.CompraTransferenciaBancaria;
import edu.uade.api.tpo.model.Publicacion;
import edu.uade.api.tpo.util.UUIDUtils;

import java.sql.*;

public class CompraTransferenciaBancariaDaoImpl extends AbstractBaseDao<CompraTransferenciaBancaria> implements CompraTransferenciaBancariaDao {

    private static CompraTransferenciaBancariaDao instance;

    private CompraTransferenciaBancariaDaoImpl() {

    }

    public static CompraTransferenciaBancariaDao getInstance() {
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
    public PreparedStatement create(CompraTransferenciaBancaria compraTransferenciaBancaria, Connection conn) throws SQLException {
        String query = "INSERT INTO " + schema + ".compras_transf_bancarias VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, UUIDUtils.generate());
        ps.setString(2, compraTransferenciaBancaria.getContraparte().getId());
        ps.setString(3, compraTransferenciaBancaria.getPublicacion().getId());
        ps.setString(4, String.valueOf(compraTransferenciaBancaria.getEstado()));
        ps.setTimestamp(5, new Timestamp(compraTransferenciaBancaria.getFecha().getTime()));
        ps.setString(6, compraTransferenciaBancaria.getEntidad().getId());
        ps.setString(7, compraTransferenciaBancaria.getNumeroCta());

        return ps;
    }

    @Override
    public PreparedStatement update(CompraTransferenciaBancaria compraTransferenciaBancaria, Connection conn) throws SQLException {
        String query = "UPDATE " + schema + ".compras_transf_bancarias SET contraparte_id = ?, publicacion_id = ?, estado = ?, fecha = ?, entidad_recaudadora_id = ?, numero_cuenta = ? where compra_transf_bancaria_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, compraTransferenciaBancaria.getContraparte().getId());
        ps.setString(2, compraTransferenciaBancaria.getPublicacion().getId());
        ps.setString(3, String.valueOf(compraTransferenciaBancaria.getEstado()));
        ps.setTimestamp(4, new Timestamp(compraTransferenciaBancaria.getFecha().getTime()));
        ps.setString(5, compraTransferenciaBancaria.getEntidad().getId());
        ps.setString(6, compraTransferenciaBancaria.getNumeroCta());
        ps.setString(7, compraTransferenciaBancaria.getId());

        return ps;
    }

    @Override
    public CompraTransferenciaBancaria map(ResultSet rs) throws SQLException {
        CompraTransferenciaBancaria compra = null;
        if (rs.first()) {
            compra = new CompraTransferenciaBancaria();
            compra.setId(rs.getString("compra_transf_bancaria_id"));
            compra.setContraparte(UsuarioDaoImpl.getInstance().findById(rs.getString("contraparte_id")));
            Publicacion pub = PublicacionDaoImpl.getInstance().findById(rs.getString("publicacion_id"));
            if (pub == null) {
                pub = SubastaDaoImpl.getInstance().findById(rs.getString("publicacion_id"));
            }
            compra.setPublicacion(pub);
            compra.setEstado(rs.getString("estado").charAt(0));
            compra.setFecha(rs.getTimestamp("fecha"));
            compra.setEntidad(EntidadRecaudadoraDaoImpl.getInstance().findById(rs.getString("entidad_recaudadora_id")));
            compra.setNumeroCta(rs.getString("numero_cuenta"));
        }

        return compra;
    }
}
