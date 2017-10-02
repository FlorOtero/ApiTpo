package edu.uade.api.tpo.model;

import edu.uade.api.tpo.dao.GenericDao;
import edu.uade.api.tpo.dao.impl.UsuarioDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class SistemaUsuarios {
    private static final Logger logger = LoggerFactory.getLogger(SistemaUsuarios.class);
    private static SistemaUsuarios instance = null;
    private GenericDao<Usuario> usuarioDao;

    private SistemaUsuarios() {
        this.usuarioDao = UsuarioDaoImpl.getInstance();
    }

    public static SistemaUsuarios getInstance() {
        if (instance == null) {
            instance = new SistemaUsuarios();
        }
        return instance;
    }

    public Usuario altaUsuario(Usuario usuario) {
        try {
            usuarioDao.create(usuario);
        } catch (SQLException e) {
            logger.error("Error creando usuario: " + usuario.getNombreUsuario(), e);
        }
        return usuario;
    }

    public void eliminarUsuario(String nombreUsuario) {
        Usuario usuario = this.buscarUsuario(nombreUsuario);
        usuario.setEstado(Estado.INACTIVO);
        try {
            usuarioDao.update(usuario);
        } catch (SQLException e) {
            logger.error("Error eliminando usuario: " + nombreUsuario, e);
        }
    }

    public void modificarUsuario(Usuario usuario) {
        try {
            usuarioDao.update(usuario);
        } catch (SQLException e) {
            logger.error("Error modificando usuario :" + usuario.getNombreUsuario(), e);
        }
    }

    public Usuario buscarUsuario(String nombreUsuario) {
        Usuario u = null;
        try {
            u = usuarioDao.findBy("nombre_usuario", nombreUsuario);
        } catch (SQLException e) {
            logger.error("Error buscando usuario :" + nombreUsuario, e);
        }
        return u;
    }
}
