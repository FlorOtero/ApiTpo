import java.util.Calendar;

public class Transaccion {
	
	private String id;
	private Usuario contraparte;
	/*
	 * Cuando se cree la clase Publicacion hay que habilitar esta 
	 * variabe y sus getter y setter */
	
	//private Publicacion publicacion;
	
	private char estado;
	private Calendar fecha;
	
	public Transaccion(String id, Usuario contraparte, char estado, Calendar fecha) {
		super();
		this.id = id;
		this.contraparte = contraparte;
		this.estado = estado;
		this.fecha = fecha;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Usuario getContraparte() {
		return contraparte;
	}

	public void setContraparte(Usuario contraparte) {
		this.contraparte = contraparte;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	

}
