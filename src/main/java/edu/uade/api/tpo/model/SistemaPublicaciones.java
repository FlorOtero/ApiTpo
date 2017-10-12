package edu.uade.api.tpo.model;

import java.util.List;

import edu.uade.api.tpo.dao.ManyToOneDao;
import edu.uade.api.tpo.dao.impl.PublicacionDaoImpl;

import java.sql.SQLException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
<<<<<<< HEAD
import edu.uade.api.tpo.dao.ManyToOneDao;
import edu.uade.api.tpo.dao.impl.PublicacionDaoImpl;
=======

>>>>>>> ABMPublicaciones
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
	
	public void eliminarSubasta(Subasta subasta) {
		subasta.setEstado(Estado.I);
		try {
			this.subastaDao.update(subasta);
		} catch (SQLException e) {
			logger.error("Error eliminando la subasta", e);
		}
	}

	public void modificarPublicacion(Publicacion p) {
		try {
			publicacionDao.update(p);
		} catch (SQLException e) {
			logger.error("Error modificando la publicacion", e);
		}
	}

	public void modificarSubasta(Subasta subasta) {
		try {
			this.subastaDao.update(subasta);
		} catch (SQLException e) {
			logger.error("Error modificando la subasta", e);
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
		Subasta subasta = new Subasta();
		subasta.setArticulo(p.getArticulo());
		subasta.setUsuarioId(p.getUsuarioId());
		subasta.setFechaDesde(p.getFechaDesde());
		subasta.setPrecioMin(precioMin);
		subasta.setPrecioInicial(pInicial);
		subasta.setEstado(Estado.A);
		subasta.setComision(p.getComision());
		subasta.setDiasVigencia(diasVigencia);
		subasta.setMediosPago(p.getMediosPago());
		
		try {
			this.publicacionDao.delete(p);
			this.subastaDao.create(subasta);
		} catch (SQLException e) {
			logger.error("Error convirtiendo publicacion a subasta", e);
		}
		return subasta;
	}
	
	public List<Publicacion> filtrarPublicaciones(String busqueda) {
		List<Publicacion> resultado = null;
		try {			
			resultado = publicacionDao.findManyLike("nombre", busqueda);
		} catch (Exception e) {
			logger.error("Error filtrando publicaciones", e);	
		}
		return resultado;
	}

	public Publicacion buscarPublicacion(String publicacionId) {
		Publicacion publicacion = null;
		try {
			return publicacionDao.findById(publicacionId);
		} catch (SQLException e) {
			logger.error("Error buscando publicacion", e);
		}
		return publicacion;
	}

	public Subasta buscarSubasta(String subastaId) {
		Subasta subasta = null;
		try {
			subasta = subastaDao.findById(subastaId);
		} catch (SQLException e) {
			logger.error("Error buscando la subasta", e);
		}
		return subasta;
	}
}
