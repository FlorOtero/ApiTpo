package edu.uade.api.tpo.model;
import java.util.Calendar;

public class Password {
	
	private String valor;
	private Calendar fechaModificacion;
	
	public Password(String valor, Calendar fechaModificacion) {
		super();
		this.valor = valor;
		this.fechaModificacion = fechaModificacion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Calendar getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Calendar fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

}
