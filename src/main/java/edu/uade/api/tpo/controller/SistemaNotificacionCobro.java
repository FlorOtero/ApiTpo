package edu.uade.api.tpo.controller;

public class SistemaNotificacionCobro {
	private static SistemaNotificacionCobro instance = null;
	
	private SistemaNotificacionCobro() {};
	
	public static SistemaNotificacionCobro getInstance() {
        if(instance == null){
            instance = new SistemaNotificacionCobro();
        }
        return instance;
    }
	

}
