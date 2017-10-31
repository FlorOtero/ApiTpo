package edu.uade.api.tpo.model;

public enum TipoPeriodo {
    MENSUAL("Meses"), ANUAL("Años");

    private String val;

    TipoPeriodo(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public static TipoPeriodo findByValue(String value){
        for(TipoPeriodo t : values()){
            if( t.val.equals(value)){
                return t;
            }
        }
        return null;
    }

}