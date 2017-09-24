
public class Domicilio {
	
	private String linea_1;
	private String linea_2;
	private String cp;
	private String ciudad;
	private String provincia;
	
	public Domicilio(String linea_1, String linea_2, String cp, String ciudad, String provincia) {
		super();
		this.linea_1 = linea_1;
		this.linea_2 = linea_2;
		this.cp = cp;
		this.ciudad = ciudad;
		this.provincia = provincia;
	}

	public String getLinea_1() {
		return linea_1;
	}

	public void setLinea_1(String linea_1) {
		this.linea_1 = linea_1;
	}

	public String getLinea_2() {
		return linea_2;
	}

	public void setLinea_2(String linea_2) {
		this.linea_2 = linea_2;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

}
