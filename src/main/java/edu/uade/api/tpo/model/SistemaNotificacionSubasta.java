package edu.uade.api.tpo.model;

import java.util.Collection;

public class SistemaNotificacionSubasta {

	private static SistemaNotificacionSubasta instance;

	public static SistemaNotificacionSubasta getInstance() {
		if(instance == null){
			instance = new SistemaNotificacionSubasta();
		}
		return instance;
	}
	
	public void notificarUsuarioGanador(String nombreUsuario, Subasta s) {
		Usuario.isGanador(s);
	}
		
	public void notificarUsuarios(Collection<String> nombreUsuarios, Subasta s) {
		// SistemaUsuarios su = SistemaUsuarios.getInstance();
		for(int i=0; i< nombreUsuarios.size(); i++) {
			// Usuario u = su.buscarUsuario(nombreUsuarios);
            // notificar(u, s);
        }
	}

}
