package edu.uade.api.tpo;

import edu.uade.api.tpo.controller.SistemaCalificaciones;
import edu.uade.api.tpo.dao.impl.TransaccionDaoImpl;
import edu.uade.api.tpo.model.Transaccion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SistemaCalificacionesTest {

    private SistemaCalificaciones sistemaCalificaciones;
    private static final String TRANSACCION_ID = "96f0dec2-19ad-4b41-bc99-4fcb8986f18d";

    @Before
    public void setup() {
        this.sistemaCalificaciones = SistemaCalificaciones.getInstance();
    }

    @Test
    public void test_calificarTransaccion() {
        try {
            Transaccion t = TransaccionDaoImpl.getInstance().findById(TRANSACCION_ID);
            this.sistemaCalificaciones.calificarTransaccion(t, 10, "lorem");
        } catch (Exception e) {
            Assert.fail("Should not throw any exception");
        }
    }

    @Test
    public void test_obtenerCalificaciones() {
        try {
            Transaccion t = TransaccionDaoImpl.getInstance().findById(TRANSACCION_ID);
            Assert.assertNotNull(t.getCalificacion());
        } catch (Exception e) {
            Assert.fail("Should not throw any exception");
        }
    }

}
