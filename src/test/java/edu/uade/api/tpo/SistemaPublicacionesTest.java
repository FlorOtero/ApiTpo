package edu.uade.api.tpo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.uade.api.tpo.model.Articulo;
import edu.uade.api.tpo.model.Garantia;
import edu.uade.api.tpo.model.MedioPago;
import edu.uade.api.tpo.model.Producto;
import edu.uade.api.tpo.model.Publicacion;
import edu.uade.api.tpo.model.Servicio;
import edu.uade.api.tpo.model.SistemaPublicaciones;
import edu.uade.api.tpo.model.Subasta;
import edu.uade.api.tpo.model.TipoContratacion;
import edu.uade.api.tpo.model.TipoPeriodo;

public class SistemaPublicacionesTest {

	private static final String USER_ID = "3e17502f-7761-4995-91d3-81412c57c27d";
	private static final String PUBLICACION_ID = "1e261566-22b4-462f-9def-b35b1da52e60";
	private static final String SUBASTA_ID = "4f8dd95c-61e5-481a-8c20-2fee2fd495c3";
	private SistemaPublicaciones sistemaPublicaciones;

	@Before
	public void setup() {
		this.sistemaPublicaciones = SistemaPublicaciones.getInstance();
	}

	@Test
	public void test_buscarPublicacion() {
		Publicacion p = this.sistemaPublicaciones.buscarPublicacion(PUBLICACION_ID);
		Assert.assertNotNull(p);
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

	@Test
	public void test_modificarPublicacion() {
		Publicacion p = this.sistemaPublicaciones.buscarPublicacion(PUBLICACION_ID);
		Articulo a = p.getArticulo();
		a.setDescripcion("Lorem ipsum..");
		p.setPrecio(123);
		p.getMediosPago().clear();
		p.getMediosPago().add(MedioPago.TRANSFERENCIA_BANCARIA);

		this.sistemaPublicaciones.modificarPublicacion(p);
	}
	
	@Test
	public void test_eliminarPublicacion() {
		Publicacion p = this.sistemaPublicaciones.buscarPublicacion(PUBLICACION_ID);
		this.sistemaPublicaciones.eliminarPublicacion(p);
		p = this.sistemaPublicaciones.buscarPublicacion(PUBLICACION_ID);
		Assert.assertNull(p);
	}
	
	@Test
	public void test_altaSubasta() {
		Servicio servicio = new Servicio();
		servicio.setNombre("Un servicio");
		servicio.setDescripcion("Descripcion del servicio");
		servicio.setContratacion(TipoContratacion.ABONO);
		servicio.fromCertificadosTokenized("asd,eewqe");
		servicio.fromImagesTokenized("asasd,asdasd");
		
		List<MedioPago> mediosPago = new ArrayList<MedioPago>();
		mediosPago.add(MedioPago.EFECTIVO);
		mediosPago.add(MedioPago.TRANSFERENCIA_BANCARIA);
		
		Subasta s = this.sistemaPublicaciones.altaSubasta(USER_ID, servicio, 105, 15, 30, mediosPago);
		Assert.assertNotNull(s);
	}
	
	@Test
	public void test_modificarSubasta() {
		Subasta subasta = this.sistemaPublicaciones.buscarSubasta(SUBASTA_ID);
		subasta.getArticulo().setDescripcion("Una descripcion");
		subasta.getArticulo().setNombre("Un nombre");
		subasta.setPrecioInicial(321);
		subasta.setPrecioMin(111);
		subasta.getMediosPago().clear();
		subasta.getMediosPago().add(MedioPago.TRANSFERENCIA_BANCARIA);
		
		this.sistemaPublicaciones.modificarSubasta(subasta);
	}
}
