import java.util.Date;

public class Password {
	
	private String valor;
	private Date fechaModificacion;
	
	public Password(String valor, Date fechaModificacion) {
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

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

}
