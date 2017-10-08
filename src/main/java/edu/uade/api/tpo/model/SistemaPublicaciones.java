package edu.uade.api.tpo.model;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uade.api.tpo.dao.ManyToOneDao;
import edu.uade.api.tpo.dao.impl.PublicacionDaoImpl;
import edu.uade.api.tpo.dao.impl.SubastaDaoImpl;

public class SistemaPublicaciones {

	private static final Logger logger = LoggerFactory.getLogger(SistemaPublicaciones.class);
	private static SistemaPublicaciones instance;
	private ManyToOneDao<Publicacion> publicacionDao;
	private ManyToOneDao<Subasta> subastaDao;

	private SistemaPublicaciones() {
		this.publicacionDao = PublicacionDaoImpl.getInstance();
		this.subastaDao = SubastaDaoImpl.getInstance();
	}

	public static SistemaPublicaciones getInstance() {
		if (instance == null) {
			instance = new SistemaPublicaciones();
		}
		return instance;
	}

	public Publicacion altaPublicacion(String usuarioId, Date fechaHasta, float precio, Articulo articulo,
			List<MedioPago> mediosPago) {
		Publicacion p = new Publicacion();
		p.setFechaDesde(new Date());
		p.setFechaHasta(fechaHasta);
		p.setPrecio(precio);
		p.setArticulo(articulo);
		p.setEstado(Estado.A);
		p.setComision(10);
		p.setUsuarioId(usuarioId);
		p.setMediosPago(mediosPago);
		try {
			publicacionDao.create(p);
		} catch (SQLException e) {
			logger.error("Error creando publicacion", e);
		}
		return p;
	}

	public void eliminarPublicacion(Publicacion publicacion) {
		publicacion.setEstado(Estado.I);
		try {
			this.publicacionDao.update(publicacion);
		} catch (SQLException e) {
			logger.error("Error eliminando publicacion", e);
		}

	}

	public void modificarPublicacion(Publicacion p) {
		try {
			publicacionDao.update(p);
		} catch (SQLException e) {
			logger.error("Error modificando la publicacion", e);
		}
	}

	public Subasta altaSubasta(String usuarioId, Articulo a, float precioMin, int diasVigencia, float precioInicial,
			List<MedioPago> mediosPago) {
		Subasta s = new Subasta();
		s.setArticulo(a);
		s.setUsuarioId(usuarioId);
		s.setFechaDesde(new Date());
		s.setPrecioMin(precioMin);
		s.setPrecioInicial(precioInicial);
		s.setEstado(Estado.A);
		s.setComision(10);
		s.setDiasVigencia(diasVigencia);
		s.setMediosPago(mediosPago);
		try {
			this.subastaDao.create(s);
		} catch (SQLException e) {
			logger.error("Error creando subasta", e);
		}

		return s;
	}

	public Subasta convertirPublicacionSubasta(Publicacion p, float precioMin, int diasVigencia, float pInicial) {
		Subasta s = null;
		return s;
	}

	public Publicacion buscarPublicacion(String publicacionId) {
		Publicacion publicacion = null;
		try {
			return publicacionDao.findById(publicacionId);
		} catch (SQLException e) {
			logger.error("Error buscando publicacion");
		}
		return publicacion;
	}

}
