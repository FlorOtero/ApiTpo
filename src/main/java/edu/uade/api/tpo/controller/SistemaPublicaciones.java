package edu.uade.api.tpo.controller;

import edu.uade.api.tpo.dao.impl.PublicacionDaoImpl;
import edu.uade.api.tpo.dao.impl.SubastaDaoImpl;
import edu.uade.api.tpo.exceptions.BusinessException;
import edu.uade.api.tpo.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.*;
import java.util.stream.Collectors;

public class SistemaPublicaciones {

    private static final Logger logger = LoggerFactory.getLogger(SistemaPublicaciones.class);
    private static SistemaPublicaciones instance;
    private PublicacionDaoImpl publicacionDao;
    private SubastaDaoImpl subastaDao;
    private List<Publicacion> publicaciones;
    private List<Subasta> subastas;

    private SistemaPublicaciones() {
        this.publicacionDao = PublicacionDaoImpl.getInstance();
        this.subastaDao = SubastaDaoImpl.getInstance();
        this.initCierreSubastaScheduler();
        try {
            this.publicaciones = publicacionDao.findAll();
            this.subastas = subastaDao.findAll();
            this.attachObservers();
        } catch (Exception e) {
            logger.error("Error obteniendo publicaciones y subastas", e);
        }
    }

    public static SistemaPublicaciones getInstance() {
        if (instance == null) {
            instance = new SistemaPublicaciones();
        }
        return instance;
    }
    		
    private void attachObservers() {
        Map<Subasta, Set<String>> subastasUsuarios = new HashMap<>();
        for (Subasta subasta : this.subastas) {
            Set<String> usuariosSubasta = new HashSet<>();
            for (Oferta oferta : subasta.getOfertas()) {
                usuariosSubasta.add(oferta.getUsuarioId());
            }
            subastasUsuarios.put(subasta, usuariosSubasta);
        }
        for (Subasta subasta : subastasUsuarios.keySet()) {
            Set<String> usuariosSubasta = subastasUsuarios.get(subasta);
            for (String usuarioId : usuariosSubasta) {
                Usuario usuario = SistemaUsuarios.getInstance().buscarUsuarioById(usuarioId);
                subasta.addObserver(usuario);
            }
        }
    }
    
    public Publicacion altaPublicacion(String usuarioId, float precio, Articulo articulo, List<MedioPago> mediosPago) {
		int DIAS_VENCIMIENTO = 90;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, DIAS_VENCIMIENTO);
		Date fechaHasta = cal.getTime();
		
		Publicacion p = new Publicacion();
        p.setFechaDesde(new Date());
        p.setFechaHasta(fechaHasta);
        p.setPrecio(precio);
        p.setArticulo(articulo);
        p.setEstado(Estado.A);
        p.setUsuarioId(usuarioId);
        p.setMediosPago(mediosPago);
        try {
            publicacionDao.create(p);
            this.publicaciones.add(p);
        } catch (SQLException e) {
            logger.error("Error creando publicacion", e);
        }
        return p;
    }

    public void eliminarPublicacion(Publicacion publicacion) {
        publicacion.setEstado(Estado.I);
        try {
            this.publicacionDao.update(publicacion);
            Publicacion pub = this.publicaciones.stream().filter(p -> p.getId().equals(publicacion.getId())).findFirst().orElse(null);
            if (pub != null) {
                pub.setEstado(Estado.I);
            }
        } catch (SQLException e) {
            logger.error("Error eliminando publicacion", e);
        }

    }

    public void eliminarSubasta(Subasta subasta) {
        subasta.setEstado(Estado.I);
        try {
            this.subastaDao.update(subasta);
            Subasta sub = this.subastas.stream().filter(s -> s.getId().equals(subasta.getId())).findFirst().orElse(null);
            if (sub != null) {
                sub.setEstado(Estado.I);
            }
        } catch (SQLException e) {
            logger.error("Error eliminando la subasta", e);
        }
    }

    public void modificarPublicacion(Publicacion publicacion) {
        try {
            publicacionDao.update(publicacion);
            Publicacion pub = this.publicaciones.stream().filter(p -> p.getId().equals(publicacion.getId())).findFirst().orElse(null);
            if (pub != null) {
                pub = publicacion;
            }
        } catch (SQLException e) {
            logger.error("Error modificando la publicacion", e);
        }
    }

    public void modificarSubasta(Subasta subasta) {
        try {
            this.subastaDao.update(subasta);
            Subasta sub = this.subastas.stream().filter(s -> s.getId().equals(subasta.getId())).findFirst().orElse(null);
            if (sub != null) {
                sub = subasta;
            }
        } catch (SQLException e) {
            logger.error("Error modificando la subasta", e);
        }
    }

    public Subasta altaSubasta(String usuarioId, Articulo a, float precioMin, int diasVigencia, float precioInicial,
                               List<MedioPago> mediosPago) {
        Subasta s = new Subasta(precioMin, diasVigencia, precioInicial);
        s.setArticulo(a);
        s.setUsuarioId(usuarioId);
        s.setFechaDesde(new Date());
        s.setEstado(Estado.A);
        s.setMediosPago(mediosPago);
        try {
            this.subastaDao.create(s);
            this.subastas.add(s);
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
        subasta.setDiasVigencia(diasVigencia);
        subasta.setMediosPago(p.getMediosPago());

        try {
            this.publicacionDao.delete(p);
            this.publicaciones.remove(p);
            this.subastaDao.create(subasta);
            this.subastas.add(subasta);
        } catch (SQLException e) {
            logger.error("Error convirtiendo publicacion a subasta", e);
        }
        return subasta;
    }

    public List<Publicacion> filtrarPublicaciones(String busqueda) {
        List<Publicacion> resultado = null;
        try {
            resultado = publicacionDao.findManyLike("nombre", busqueda);
            resultado.addAll(subastaDao.findManyLike("nombre", busqueda));
        } catch (Exception e) {
            logger.error("Error filtrando publicaciones", e);
        }
        return resultado;
    }

    public Publicacion buscarPublicacion(String publicacionId) {
        Publicacion publicacion = null;
        if (this.publicaciones != null && !this.publicaciones.isEmpty()) {
            publicacion = publicaciones.stream().filter(p -> p.getId().equals(publicacionId)).findFirst().orElse(null);
        }
        if (publicacion == null) {
            try {
                return publicacionDao.findById(publicacionId);
            } catch (SQLException e) {
                logger.error("Error buscando publicacion", e);
            }
        }
        return publicacion;
    }

    public Subasta buscarSubasta(String subastaId) {
        Subasta subasta = null;
        if (this.subastas != null && !this.subastas.isEmpty()) {
            subasta = subastas.stream().filter(s -> s.getId().equals(subastaId)).findFirst().orElse(null);
        }
        if (subasta == null) {
            try {
                subasta = subastaDao.findById(subastaId);
            } catch (SQLException e) {
                logger.error("Error buscando la subasta", e);
            }
        }
        return subasta;
    }

    public void ofertar(Publicacion publicacion, float monto, Usuario contraparte, DatosPago datosPago) throws BusinessException {
        if (publicacion == null) {
            throw new BusinessException("No se encontro la publicacion");
        }
        publicacion.ofertar(monto, contraparte, datosPago);
    }

    /**
     * Verifica cada 10 minutos si existen subastas pendientes de ser cerradas y las cierra
     */
    private void initCierreSubastaScheduler() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            logger.info(">>> Buscando subastas pendientes de cierre <<<");
            List<Subasta> subastasActivas = this.subastas.stream().filter(s -> s.hasEnded()).collect(Collectors.toList());
            subastasActivas.forEach(subastaActiva -> {
                Oferta oferta = subastaActiva.obtenerMayorOferta();
                if(oferta != null && oferta.getMonto() >= subastaActiva.getPrecioMin()) {
                    Usuario usuarioSubasta = SistemaUsuarios.getInstance().buscarUsuarioById(oferta.getUsuarioId());
                    subastaActiva.cerrar(usuarioSubasta, oferta.getDatosPago());
                } else {
                    //Si no tuvo ofertas, la doy de baja
                    this.eliminarSubasta(subastaActiva);
                }
            });
        };
        scheduler.scheduleAtFixedRate(task, 0, 10, TimeUnit.MINUTES);
    }


}
