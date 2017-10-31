package edu.uade.api.tpo.model;

public enum TipoContratacion {

    ABONO("Abono"), UNICA("Unica");

    private String val;

    TipoContratacion(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public static TipoContratacion findByValue(String value) {
        for (TipoContratacion t : values()) {
            if (t.val.equals(value)) {
                return t;
            }
        }
        return null;
    }

}
