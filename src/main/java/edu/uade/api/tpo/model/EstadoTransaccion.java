package edu.uade.api.tpo.model;

public enum EstadoTransaccion {

    P, //pendiente
    A, //aprobada
	C; //cancelada

    private String val;

    EstadoTransaccion() {}

    EstadoTransaccion(String val) {
        this.val = val;
    }
    public String getVal() {
        return val;
    }
}
