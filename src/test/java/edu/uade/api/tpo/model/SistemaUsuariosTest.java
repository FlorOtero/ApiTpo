package edu.uade.api.tpo.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SistemaUsuariosTest {

    private SistemaUsuarios sistemaUsuarios;

    @Before
    public void setup() {
        this.sistemaUsuarios = SistemaUsuarios.getInstance();
    }

    @Test
    public void test_findUsuario() {
        Usuario u = sistemaUsuarios.buscarUsuario("flor");
        Assert.assertNotNull(u);
    }

    @Test
    public void test_modificarUsuario() {
        Usuario u = sistemaUsuarios.buscarUsuario("flor");
        u.setNombre("un nombre");
        u.setApellido("un apellido");
        try {
            sistemaUsuarios.modificarUsuario(u);
            u = sistemaUsuarios.buscarUsuario("flor");
            Assert.assertEquals("un nombre", u.getNombre());
            Assert.assertEquals("un apellido", u.getApellido());

            //Vuelvo atrás los cambios
            u.setNombre("Flor");
            u.setApellido("Otero");
            sistemaUsuarios.modificarUsuario(u);
        } catch (Exception e) {
            Assert.fail("Should not throw any exception");
        }
    }
}
