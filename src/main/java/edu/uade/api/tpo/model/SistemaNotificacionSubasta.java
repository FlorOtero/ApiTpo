package edu.uade.api.tpo.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SistemaNotificacionSubasta {

	private static SistemaNotificacionSubasta instance = null;
	private static final Logger logger = LoggerFactory.getLogger(SistemaNotificacionSubasta.class);
	private SistemaNotificacionSubasta() {}

	public static SistemaNotificacionSubasta getInstance() {
		if(instance == null){
			instance = new SistemaNotificacionSubasta();
		}
		return instance;
	}

	public void notificarUsuarioSubasta(String userId, Subasta subasta) {
		logger.info(">>> Notificando Usuario: "+userId);
	}
}
