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
        
    }

}
