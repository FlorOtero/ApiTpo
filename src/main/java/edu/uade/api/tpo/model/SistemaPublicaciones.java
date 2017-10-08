package edu.uade.api.tpo.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uade.api.tpo.dao.GenericDao;
import edu.uade.api.tpo.dao.impl.PublicacionDaoImpl;
import edu.uade.api.tpo.dao.impl.UsuarioDaoImpl;
import edu.uade.api.tpo.exceptions.BusinessException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class SistemaPublicaciones {
	private static final Logger logger = LoggerFactory.getLogger(SistemaPublicaciones.class);
	private static SistemaPublicaciones instance = null;
	private List<Publicacion> publicaciones;
	private GenericDao<Publicacion> publicacionesDao;
	
	
	  private SistemaPublicaciones() {
	        this.publicacionesDao = PublicacionDaoImpl.getInstance();
	    }
	
	
	public static SistemaPublicaciones getInstance() {
        if(instance == null){
            instance = new SistemaPublicaciones();
        }
        return instance;
    }
	 
	public Publicacion crearPublicacion(Publicacion publicacion) throws BusinessException {
        if (this.buscarPublicacion(publicacion.getArticulo().getNombre())== null) {
            try {
            	publicacionesDao.create(publicacion);
            } catch (SQLException e) {
                logger.error("Error creando publicacion: " + publicacion.getArticulo().getNombre(), e);
            }
            return publicacion;
        } else {
            throw new BusinessException("La publicacion ya existe");
        }
		
	}
	
    public Publicacion buscarPublicacion(String nombrePublicacion) {
        Publicacion publi = null;
        try {
            publi = publicacionesDao.findBy("nombre_publicacion", nombrePublicacion);
        } catch (SQLException e) {
            logger.error("Error buscando publicacion :" + nombrePublicacion, e);
        }
        return publi;
    }
    
	public void eliminarPublicacion(Publicacion publicacion) {
		this.publicaciones.remove(publicacion);
	}
	
	public void modificarPublicacion(Publicacion p, Date fechaDesde, Date fechaHasta, float precio, Articulo articulo) {
		p.setFechaDesde(fechaDesde);
		p.setFechaHasta(fechaHasta);
		p.setPrecio(precio);
		p.setArticulo(articulo);
	}
	
	public Subasta crearSubasta(Usuario u, Articulo a, float precioMin, int diasVigencia, float precioInicial) {
		Subasta s = null;
		return s;
	}
	
	public Subasta convertirPublicacionSubasta(Publicacion p, float precioMin, int diasVigencia, float pInicial) {
		Subasta s = null;
		return s;
	}
	
}
