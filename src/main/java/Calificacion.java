import java.util.Calendar;

public class Calificacion {

	private Transaccion transaccion;
	private int calificacion;
	private String observaciones;
	private Calendar fecha;
	private char estado;
	
	public Calificacion(Transaccion transaccion, int calificacion, String observaciones, Calendar fecha, char estado) {
		super();
		this.transaccion = transaccion;
		this.calificacion = calificacion;
		this.observaciones = observaciones;
		this.fecha = fecha;
		this.estado = estado;
	}

	public Transaccion getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Transaccion transaccion) {
		this.transaccion = transaccion;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

}
