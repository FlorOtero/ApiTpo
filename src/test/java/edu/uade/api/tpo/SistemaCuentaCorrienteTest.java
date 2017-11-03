package edu.uade.api.tpo;

import edu.uade.api.tpo.controller.SistemaCuentaCorriente;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SistemaCuentaCorrienteTest {

    private SistemaCuentaCorriente sistemaCuentaCorriente;

    @Before
    public void setup() {
        this.sistemaCuentaCorriente = SistemaCuentaCorriente.getInstance();
    }

    @Test
    public void test_consultarMovimientos() {
        try {
            this.sistemaCuentaCorriente.consultarMovimientos("agustin");
        } catch (Exception e) {
            Assert.fail("Should not throw any exception");
        }

    }

}
