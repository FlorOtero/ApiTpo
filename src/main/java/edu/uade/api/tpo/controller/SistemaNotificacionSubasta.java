package edu.uade.api.tpo.controller;

import edu.uade.api.tpo.model.Subasta;
import edu.uade.api.tpo.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SistemaNotificacionSubasta {

    private static SistemaNotificacionSubasta instance = null;
    private static final Logger logger = LoggerFactory.getLogger(SistemaNotificacionSubasta.class);

    private SistemaNotificacionSubasta() {

    }

    public static SistemaNotificacionSubasta getInstance() {
        if (instance == null) {
            instance = new SistemaNotificacionSubasta();
        }
        return instance;
    }

    public void notificarUsuarioGanadorSubasta(Usuario usuario, Subasta subasta) {
        logger.info("***************************************************");
        logger.info("Felicidades " + usuario.getNombre() + " eres el ganador de la subasta " + subasta.getArticulo().getNombre());
        logger.info("***************************************************");
    }

    public void notificarUsuarioPerdedorSubasta(Usuario usuario, Subasta subasta) {
        logger.info("***************************************************");
        logger.info("Atencion " + usuario.getNombre() + " han superado tu oferta en la subasta " + subasta.getArticulo().getNombre());
        logger.info("***************************************************");
    }
}
