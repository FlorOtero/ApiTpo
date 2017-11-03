package edu.uade.api.tpo.model;

import edu.uade.api.tpo.db.Persistible;

import java.util.ArrayList;
import java.util.List;

public class CuentaCorriente implements Persistible {

    private String id;
    private float saldo;
    private List<Transaccion> transacciones;
    private String usuarioId;

    public CuentaCorriente(String usuarioId) {
        this.saldo = 0;
        this.transacciones = new ArrayList<>();
        this.usuarioId = usuarioId;
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

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }
}
