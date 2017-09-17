package edu.uade.api.tpo.model;

import java.io.Serializable;
import java.util.Collection;

public class CuentaCorriente implements Serializable {

    private float saldo;
    private Collection<Transaccion> transacciones;

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public Collection<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(Collection<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }
}
