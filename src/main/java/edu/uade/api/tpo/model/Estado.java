package edu.uade.api.tpo.model;

public enum Estado {

    A,//activa
    P,//pausada
    I;//inactiva

    private String val;

    Estado() {}

    Estado(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

}
