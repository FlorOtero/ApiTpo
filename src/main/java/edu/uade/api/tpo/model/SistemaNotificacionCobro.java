package edu.uade.api.tpo.model;

import javax.management.Notification;

public class SistemaNotificacionCobro {
	private String mensaje;
	private static SistemaNotificacionCobro instance = null;
	
	private SistemaNotificacionCobro() {};
	
	public static SistemaNotificacionCobro getInstance() {
        if(instance == null){
            instance = new SistemaNotificacionCobro();
        }
        return instance;
    }
	

}
