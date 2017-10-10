package edu.uade.api.tpo.model;

public enum TipoPeriodo {
    MENSUAL, ANUAL;

private String val;

TipoPeriodo() {}

TipoPeriodo(String val) {
    this.val = val;
}

public String getVal() {
    return val;
}
}