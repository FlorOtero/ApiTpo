package edu.uade.api.tpo.model;

public enum EstadoTransaccion {

    P, //pendiente
    A, //aprobada
	C; //cancelada

    private char val;

    EstadoTransaccion() {}

    EstadoTransaccion(char val) {
        this.val = val;
    }
    public char getVal() {
        return val;
    }
}
