package edu.uade.api.tpo;

import edu.uade.api.tpo.controller.SistemaUsuarios;
import edu.uade.api.tpo.dao.impl.UsuarioDaoImpl;
import edu.uade.api.tpo.exceptions.BusinessException;
import edu.uade.api.tpo.controller.SistemaPublicaciones;
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
    private static final String CONTRAPARTE_ID = "041b9ec5-d99d-4f80-8db8-d3a69fa5b137";
    private static final String PUBLICACION_ID = "1320ad84-3645-4aa4-b6b4-c103528ca71e";
    private static final String SUBASTA_ID = "418aab94-8cf6-4bce-bb3b-715d41fb8863";
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
        servicio.setNombre("Un servicio test");
        servicio.setDescripcion("Descripcion del servicio test");
        servicio.setContratacion(TipoContratacion.ABONO);
        servicio.fromCertificadosTokenized("asd,eewqe");
        servicio.fromImagesTokenized("asasd,asdasd");

        List<MedioPago> mediosPago = new ArrayList<MedioPago>();
        mediosPago.add(MedioPago.EFECTIVO);
        mediosPago.add(MedioPago.TRANSFERENCIA_BANCARIA);

        Subasta s = this.sistemaPublicaciones.altaSubasta(USER_ID, servicio, 500, 15, 200, mediosPago);
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
            Usuario usuario = UsuarioDaoImpl.getInstance().findById("8d39b0c0-d8b6-42c9-9653-b80c5b29f7e5");
            DatosPago datosPago = new DatosPago();
            datosPago.setNumeroCuenta("12345");
            datosPago.setMedioPago(MedioPago.TRANSFERENCIA_BANCARIA);
            this.sistemaPublicaciones.ofertar(subasta, 380, usuario, datosPago);
        } catch (SQLException | BusinessException e) {
            e.printStackTrace();
            Assert.fail("Should not throw exception");
        }
    }

    @Test
    public void test_cerrarSubasta() {
        String ganadorId = "8d39b0c0-d8b6-42c9-9653-b80c5b29f7e5";
        Subasta subasta = this.sistemaPublicaciones.buscarSubasta("418aab94-8cf6-4bce-bb3b-715d41fb8863");
        Oferta oferta = subasta.obtenerMayorOferta();
        subasta.cerrar(SistemaUsuarios.getInstance().buscarUsuarioById(ganadorId), oferta.getDatosPago());
    }

    @Test
    public void test_ofertarSubastaPrecioInferior() {
        Subasta subasta = this.sistemaPublicaciones.buscarSubasta(SUBASTA_ID);
        try {
            Usuario usuario = UsuarioDaoImpl.getInstance().findById(USER_ID);
            DatosPago datosPago = new DatosPago();
            datosPago.setNumeroCuenta("12345");
            datosPago.setMedioPago(MedioPago.TRANSFERENCIA_BANCARIA);
            this.sistemaPublicaciones.ofertar(subasta, 190, usuario, datosPago);
            Assert.fail("Should throw exception");
        } catch (SQLException | BusinessException e) {
            Assert.assertEquals("El monto ofertado es menor que el precio actual", e.getMessage());
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

    @Test
    public void test_ofertarPublicacionEfectivo() {
        Publicacion publicacion = this.sistemaPublicaciones.buscarPublicacion(PUBLICACION_ID);
        try {
            Usuario contraparte = UsuarioDaoImpl.getInstance().findById(CONTRAPARTE_ID);
            DatosPago datosPago = new DatosPago();
            datosPago.setMedioPago(MedioPago.EFECTIVO);
            this.sistemaPublicaciones.ofertar(publicacion, 666, contraparte, datosPago);
        } catch (SQLException | BusinessException e) {
            e.printStackTrace();
            Assert.fail("Should not throw exception");
        }
    }

    @Test
    public void test_ofertarPublicacionTarjetaCredito() {
        Publicacion publicacion = this.sistemaPublicaciones.buscarPublicacion(PUBLICACION_ID);
        try {
            Usuario contraparte = UsuarioDaoImpl.getInstance().findById(CONTRAPARTE_ID);
            DatosPago datosPago = new DatosPago();
            datosPago.setMedioPago(MedioPago.TARJETA_CREDITO);
            datosPago.setNumeroTarjeta("123123");
            this.sistemaPublicaciones.ofertar(publicacion, 666, contraparte, datosPago);
        } catch (SQLException | BusinessException e) {
            e.printStackTrace();
            Assert.fail("Should not throw exception");
        }
    }
}
