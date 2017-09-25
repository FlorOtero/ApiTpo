package edu.uade.api.tpo.model;

public class SistemaNotificacionSubasta {

	public SistemaNotificacionSubasta() {
		public void notificarUsuarioGanador(String nombreUsuario, Subasta s){
			Usuario.sosGanador(s);
		}
		
		public void notificarUsuarios(collection nombreUsuarios, Subasta s){
			for(int i=0; i< nombreUsuarios.size(); i++) {
	            notificar(nombreUsuario.get(i), s);
	        }
		}
		
	}

}
