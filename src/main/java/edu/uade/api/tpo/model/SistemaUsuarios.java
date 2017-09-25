package edu.uade.api.tpo.model;

import java.util.ArrayList;
import java.util.List;

public class SistemaUsuarios {
	private static SistemaUsuarios instance = null;
	private List<Usuario> usuarios; 
	
	private SistemaUsuarios() {}
	
	public static SistemaUsuarios getInstance() {
        if(instance == null){
            instance = new SistemaUsuarios();
        }
        return instance;
    }
	
	public Usuario altaUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
		return usuario;
	}
	
	public void eliminarUsuario(String nombreUsuario) {
		Usuario user = null;
		user = this.buscarUsuario(nombreUsuario);
		this.usuarios.remove(user);
	}
	
	public void modificarUsuario(String nombreUsuario, String nombre, String apellido, String domicilio, String mail, Password p) {
		Usuario user = null;
		user = this.buscarUsuario(nombreUsuario);
		user.setNombreUsuario(nombreUsuario);
		// TODO: update all values when setters are available
	}
	
	public Usuario buscarUsuario(String nombreUsuario) {
		Usuario user = null;
		for (Usuario u: this.usuarios) {
			if (u.getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
				user = u; 
			}
		}
		return user;
	}
}
