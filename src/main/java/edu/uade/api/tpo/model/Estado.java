package edu.uade.api.tpo.model;

public enum Estado {

    ACTIVO("A"),
    INACTIVO("I");

    private String value;

    Estado(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
