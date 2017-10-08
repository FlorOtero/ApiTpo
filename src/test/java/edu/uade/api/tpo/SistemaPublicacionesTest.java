package edu.uade.api.tpo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.uade.api.tpo.model.Garantia;
import edu.uade.api.tpo.model.MedioPago;
import edu.uade.api.tpo.model.Producto;
import edu.uade.api.tpo.model.SistemaPublicaciones;
import edu.uade.api.tpo.model.TipoPeriodo;

public class SistemaPublicacionesTest {

	private static final String USER_ID = "9ec1f480-b1a3-4605-a808-26829333e09d";
	private SistemaPublicaciones sistemaPublicaciones;

	@Before
	public void setup() {
		this.sistemaPublicaciones = SistemaPublicaciones.getInstance();
	}

	@Test
	public void test_altaPublicacion() {
		Producto articulo = new Producto();
		articulo.setDescripcion("Desc");
		articulo.fromImagesTokenized("asd,ewqe");
		articulo.setNombre("Un nombre");

		Garantia garantia = new Garantia();
		garantia.setCantidad(12);
		garantia.setTipo(TipoPeriodo.MENSUAL);
		articulo.setGarantia(garantia);

		List<MedioPago> mediosPago = new ArrayList<MedioPago>();
		mediosPago.add(MedioPago.EFECTIVO);
		mediosPago.add(MedioPago.TARJETA_CREDITO);

		this.sistemaPublicaciones.altaPublicacion(USER_ID, new Date(), 300, articulo, mediosPago);
	}
}
