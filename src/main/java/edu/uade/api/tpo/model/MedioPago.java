package edu.uade.api.tpo.model;

import edu.uade.api.tpo.db.Persistible;

public enum MedioPago implements Persistible {

    EFECTIVO("0ea15b57-85ad-49b3-af04-080dac974678"),
    TRANSFERENCIA_BANCARIA("b166bf70-0ea9-4fc2-b803-40fd6e7cb8b8"),
    TARJETA_CREDITO("be5c6b74-48f6-475d-ad87-a08ece31edde");

    private String id;
    
    public static MedioPago getMedioPago(String medioPagoId) {
        for (MedioPago m : MedioPago.values()) {
            if (m.getId().equals(medioPagoId)) return m;
        }
		throw new IllegalArgumentException("Value " + medioPagoId + " unknown");
     }

    MedioPago(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}
