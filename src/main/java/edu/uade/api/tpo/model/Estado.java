package edu.uade.api.tpo.model;

public enum Estado {

    A,
    I;

    private String val;

    Estado() {}

    Estado(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

}
