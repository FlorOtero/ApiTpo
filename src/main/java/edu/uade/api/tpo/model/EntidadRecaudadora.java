package edu.uade.api.tpo.model;

public abstract class EntidadRecaudadora<T extends > {

    public abstract void informarPago(Transaccion transaccion);
}
