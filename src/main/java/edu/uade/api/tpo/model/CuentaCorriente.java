package edu.uade.api.tpo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CuentaCorriente implements Serializable {

    private String id;
    private float saldo;
    private List<Transaccion> transacciones;

    public CuentaCorriente() {
        this.transacciones = new ArrayList<>();
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public void addTransaccion(Transaccion transaccion) {
        this.transacciones.add(transaccion);
    }

    public void removeTransaccion(Transaccion transaccion) {
        this.transacciones.remove(transaccion);
    }

    public boolean hasTransacciones() {
        return !this.transacciones.isEmpty();
    }

    public boolean hasTransaccion(Transaccion transaccion) {
        return this.transacciones.contains(transaccion);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
