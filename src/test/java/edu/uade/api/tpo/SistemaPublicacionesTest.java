package edu.uade.api.tpo;

import edu.uade.api.tpo.dao.impl.UsuarioDaoImpl;
import edu.uade.api.tpo.exceptions.BusinessException;
import edu.uade.api.tpo.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	public void test_buscarSubasta() {
		Subasta subasta = this.sistemaPublicaciones.buscarSubasta(SUBASTA_ID);
		Assert.assertNotNull(subasta);
		Assert.assertTrue(!subasta.getOfertas().isEmpty());
	}

	@Test
	public void test_altaPublicacionProducto() {
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
	public void test_altaPublicacionServicio() {
		Servicio servicio = new Servicio();
		servicio.setDescripcion("ASDASD");
		servicio.setNombre("Etc etc");
		servicio.fromCertificadosTokenized("certificados");
		servicio.fromImagesTokenized("images");
		servicio.setContratacion(TipoContratacion.ABONO);

		List<MedioPago> mediosPago = new ArrayList<>();
		mediosPago.add(MedioPago.EFECTIVO);
		this.sistemaPublicaciones.altaPublicacion(USER_ID, new Date(), 300, servicio, mediosPago);
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

	@Test
	public void test_ofertarSubasta() {
		Subasta subasta = this.sistemaPublicaciones.buscarSubasta(SUBASTA_ID);
		try {
			Usuario usuario = UsuarioDaoImpl.getInstance().findById(USER_ID);
			subasta.ofertar(200, usuario, MedioPago.TRANSFERENCIA_BANCARIA);
		} catch (SQLException | BusinessException e) {
			Assert.fail("Should not throw exception");
		}
	}

	@Test
	public void test_eliminarSubasta() {
		Subasta subasta = this.sistemaPublicaciones.buscarSubasta(SUBASTA_ID);
		this.sistemaPublicaciones.eliminarSubasta(subasta);
		subasta = this.sistemaPublicaciones.buscarSubasta(SUBASTA_ID);
		Assert.assertNull(subasta);
	}

	@Test
	public void test_convertirPublicacionSubasta() {
		Publicacion p = this.sistemaPublicaciones.buscarPublicacion(PUBLICACION_ID);
		Subasta s = this.sistemaPublicaciones.convertirPublicacionSubasta(p, 666, 30, 400);
		Assert.assertNotNull(s);
	}

	@Test
	public void test_filtrarPublicaciones() {
		List<Publicacion> publicaciones = this.sistemaPublicaciones.filtrarPublicaciones("Compu");
		Assert.assertTrue(publicaciones.size() == 2);
	}
}
