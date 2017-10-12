package edu.uade.api.tpo.model;

@FunctionalInterface
public interface EntidadRecaudadora<T extends Transaccion> {

    public void informarPago(T t);

}
