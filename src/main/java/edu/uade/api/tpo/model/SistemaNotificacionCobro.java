package edu.uade.api.tpo.model;

import javax.management.Notification;

public class SistemaNotificacionCobro {
	private String mensaje;
	private static SistemaNotificacionCobro instance;
	
	public static SistemaNotificacionCobro getInstance() {
        if(instance == null){
            instance = new SistemaNotificacionCobro();
        }
        return instance;
    }
	
//	public String notificar(){	
//		return mensaje;
//	}

}
