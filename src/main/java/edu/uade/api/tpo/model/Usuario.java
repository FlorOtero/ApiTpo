package edu.uade.api.tpo.model;

import edu.uade.api.tpo.controller.SistemaNotificacionSubasta;
import edu.uade.api.tpo.db.Persistible;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Usuario implements Persistible, Observer {

    private String id;
    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private Domicilio domicilio;
    private Password password;
    private CuentaCorriente cuentaCorriente;
    private List<Publicacion> publicaciones;
    private String mail;
    private Estado estado;

    public Usuario() {
        this.cuentaCorriente = new CuentaCorriente();
        this.publicaciones = new ArrayList<>();
        this.estado = Estado.A;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public CuentaCorriente getCuentaCorriente() {
        return cuentaCorriente;
    }

    public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public void addPublicacion(Publicacion publicacion) {
        this.publicaciones.add(publicacion);
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public float calcularReputacion() {
        
    		float reputacion = 0;
    		int aprobadas = 0;
    	
    		for(Transaccion tr : cuentaCorriente.getTransacciones()){
    			if(tr.getEstado() == EstadoTransaccion.A && tr.getCalificacion() != null) {
    				reputacion += tr.getCalificacion().getCalificacion();
    				aprobadas++;
    			}
    		}
    		
    		return (aprobadas == 0) ? 0 : (reputacion/aprobadas);
    
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Subasta) {
            Subasta subasta = (Subasta) o;
            Oferta oferta = subasta.obtenerMayorOferta();
            if (subasta.hasEnded()) {
                if (oferta.getUsuarioId().equals(this.getId())) {
                    SistemaNotificacionSubasta.getInstance().notificarUsuarioGanadorSubasta(this, subasta);
                }
            } else {
                if (!oferta.getUsuarioId().equals(this.getId())) {
                    SistemaNotificacionSubasta.getInstance().notificarUsuarioPerdedorSubasta(this, subasta);
                }
            }
        }
    }
}
