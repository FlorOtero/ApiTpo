package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.AbstractManyToOneDao;
import edu.uade.api.tpo.dao.ManyToOneDao;
import edu.uade.api.tpo.model.Estado;
import edu.uade.api.tpo.model.MedioPago;
import edu.uade.api.tpo.model.Publicacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicacionDaoImpl extends AbstractManyToOneDao<Publicacion> {

	private static ManyToOneDao<Publicacion> instance;

	private PublicacionDaoImpl() {
	}

	public static ManyToOneDao<Publicacion> getInstance() {
		if (instance == null) {
			instance = new PublicacionDaoImpl();
		}
		return instance;
	}

	@Override
	public PreparedStatement create(Publicacion publicacion, Connection conn) throws SQLException {
		ArticuloDaoImpl.getInstance().create(publicacion.getArticulo());

		String query = "INSERT INTO " + schema + ".publicaciones VALUES(?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, publicacion.getId());
		ps.setString(2, publicacion.getUsuarioId());
		ps.setTimestamp(3, new Timestamp(publicacion.getFechaDesde().getTime()));
		ps.setTimestamp(4, new Timestamp(publicacion.getFechaHasta().getTime()));
		ps.setFloat(5, publicacion.getPrecio());
		ps.setString(6, String.valueOf(publicacion.getEstado()));
		ps.setString(7, publicacion.getArticulo().getId());
		return ps;
	}

	@Override
	public void doAfterCreate(Publicacion publicacion) throws SQLException {
		if (publicacion.getMediosPago() != null && !publicacion.getMediosPago().isEmpty()) {
			for (MedioPago medioPago : publicacion.getMediosPago()) {
				MedioPagoDaoImpl.getInstance().addMedioPagoToPublicacion(publicacion.getId(), medioPago);
			}
		}
	}

	@Override
	public PreparedStatement findById(String id, Connection conn) throws SQLException {
		String query = "SELECT * FROM " + schema + ".publicaciones WHERE publicacion_id = ? AND estado = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, id);
		ps.setString(2, Estado.A.toString());
		return ps;
	}

	@Override
	public Publicacion map(ResultSet rs) throws SQLException {
		Publicacion publicacion = null;
		if (rs.first()) {
			publicacion = mapRow(rs);
		}

		return publicacion;
	}

	@Override
	public PreparedStatement update(Publicacion publicacion, Connection conn) throws SQLException {
		ArticuloDaoImpl.getInstance().update(publicacion.getArticulo());
		String query = "UPDATE " + schema
				+ ".publicaciones SET usuario_id = ?, fecha_desde = ?, fecha_hasta = ?, precio = ?, estado = ?, articulo_id = ? WHERE publicacion_id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, publicacion.getUsuarioId());
		ps.setTimestamp(2, new Timestamp(publicacion.getFechaDesde().getTime()));
		ps.setTimestamp(3, new Timestamp(publicacion.getFechaHasta().getTime()));
		ps.setFloat(4, publicacion.getPrecio());
		ps.setString(5, String.valueOf(publicacion.getEstado()));
		ps.setString(6, publicacion.getArticulo().getId());
		ps.setString(7, publicacion.getId());
		if (publicacion.getMediosPago() != null && !publicacion.getMediosPago().isEmpty()) {
			MedioPagoDaoImpl.getInstance().updateMediosPagoToPublicacion(publicacion.getId(),
					publicacion.getMediosPago());
		}
		return ps;
	}

	@Override
	public PreparedStatement findManyBy(String field, String value, Connection conn) throws SQLException {
		String query = "SELECT * FROM " + schema + ".publicaciones WHERE " + field + " = ? AND estado = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, value);
		ps.setString(2, Estado.A.toString());
		return ps;
	}
	
	@Override
    public PreparedStatement findManyLike(String field, String value, Connection conn) throws SQLException {
		String query = "SELECT publicacion_id, usuario_id, fecha_desde, fecha_hasta, precio, estado, articulo_id, producto_id, nombre, descripcion FROM " + schema + ".publicaciones AS publicacion, " + schema + ".productos AS producto " +
				"WHERE lower(producto." + field + ") LIKE ? AND publicacion.articulo_id = producto.producto_id AND publicacion.estado = 'A' " +
				"UNION ALL " +
				"SELECT publicacion_id, usuario_id, fecha_desde, fecha_hasta, precio, estado, articulo_id, servicio_id, nombre, descripcion FROM " + schema + ".publicaciones AS publicacion, " + schema + ".servicios AS servicio " +
				"WHERE lower(servicio." + field + ") LIKE ? AND publicacion.articulo_id = servicio.servicio_id AND publicacion.estado = 'A' " +
				"UNION ALL " +
				"SELECT subasta_id, usuario_id, fecha_desde, fecha_hasta, precio, estado, articulo_id, servicio_id, nombre, descripcion FROM " + schema + ".subastas AS subasta, " + schema + ".servicios AS servicio " +
				"WHERE lower(servicio." + field + ") LIKE ? AND subasta.articulo_id = servicio.servicio_id AND subasta.estado = 'A'";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, "%" + value + "%");
		ps.setString(2, "%" + value + "%");
		ps.setString(3, "%" + value + "%");
		return ps;
    }

	@Override
	public List<Publicacion> mapMany(ResultSet rs) throws SQLException {
		List<Publicacion> publicaciones = new ArrayList<>();
		while (rs.next()) {
			publicaciones.add(mapRow(rs));
		}
		return publicaciones;
	}

	private Publicacion mapRow(ResultSet rs) throws SQLException {
		Publicacion publicacion = new Publicacion();
		publicacion.setId(rs.getString("publicacion_id"));
		publicacion.setUsuarioId(rs.getString("usuario_id"));
		publicacion.setFechaDesde(rs.getTimestamp("fecha_desde"));
		publicacion.setFechaHasta(rs.getTimestamp("fecha_hasta"));
		publicacion.setPrecio(rs.getFloat("precio"));
		publicacion.setEstado(Estado.valueOf(rs.getString("estado")));
		publicacion.setArticulo(ArticuloDaoImpl.getInstance().findById(rs.getString("articulo_id")));
		publicacion.setMediosPago(MedioPagoDaoImpl.getInstance().findByPublicacionId(publicacion.getId()));
		return publicacion;
	}

	@Override
	public PreparedStatement findBy(String field, String value, Connection conn) throws SQLException {
		throw new UnsupportedOperationException("Find by is not supported on class Publicacion!");
	}

	@Override
	public void delete(Publicacion t) throws SQLException {
		MedioPagoDaoImpl.getInstance().deleteByPublicacionId(t.getId());
		try (Connection conn = this.getConnection(); PreparedStatement ps = getDeleteStatement(t, conn)) {
			ps.execute();
		}
		ArticuloDaoImpl.getInstance().delete(t.getArticulo());
	}

	private PreparedStatement getDeleteStatement(Publicacion p, Connection conn) throws SQLException {
		String query = "DELETE FROM " + schema + ".publicaciones WHERE publicacion_id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, p.getId());
		return ps;
	}
}
