package edu.uade.api.tpo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.uade.api.tpo.exceptions.InvalidPasswordException;
import edu.uade.api.tpo.model.MedioPago;
import edu.uade.api.tpo.model.SistemaUsuarios;
import edu.uade.api.tpo.model.Usuario;

public class SistemaUsuariosTest {

    private SistemaUsuarios sistemaUsuarios;

    @Before
    public void setup() {
        this.sistemaUsuarios = SistemaUsuarios.getInstance();
    }

    @Test
    public void test_findUsuario() {
        Usuario u = sistemaUsuarios.buscarUsuario("asd");
        Assert.assertNotNull(u);
    }

    @Test
    public void test_modificarUsuario() {
        Usuario u = sistemaUsuarios.buscarUsuario("flor");
        u.setNombre("un nombre");
        u.setApellido("un apellido");
        u.getPassword().setValor("54321");
        try {
            sistemaUsuarios.modificarUsuario(u);
            u = sistemaUsuarios.buscarUsuario("flor");
            Assert.assertEquals("un nombre", u.getNombre());
            Assert.assertEquals("un apellido", u.getApellido());
            Assert.assertEquals("54321", u.getPassword().getValor());
            //Vuelvo atrás los cambios
            u.setNombre("Flor");
            u.setApellido("Otero");
            u.getPassword().setValor("12345");
            
            sistemaUsuarios.modificarUsuario(u);
        } catch (Exception e) {
            Assert.fail("Should not throw any exception");
        }
    }

    @Test
    public void test_eliminarUsuario() {
        try {
            sistemaUsuarios.eliminarUsuario("flor");
            Usuario u = sistemaUsuarios.buscarUsuario("flor");
            Assert.assertNull(u);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Should not throw any exception");
        }
    }

    @Test
    public void test_validarPasswordOk() {
        String password = "unPasswordOk1";
        try {
            sistemaUsuarios.validarPassword(password);
        } catch (Exception e) {
            Assert.fail("Should not throw any exception");
        }
    }

    @Test
    public void test_validarPasswordErr() {
        String password = "unpassword";
        try {
            sistemaUsuarios.validarPassword(password);
            Assert.fail("Should throw exception since password is invalid");
        } catch (InvalidPasswordException e) {
            Assert.assertEquals("La contraseña ingresada es incorrecta.\nLa misma debe tener entre 8 y 20 caracteres, al menos un número y al menos una letra mayúscula.", e.getMessage());
        } catch (Exception e) {
            Assert.fail("Should not throw any exception");
        }
    }
}