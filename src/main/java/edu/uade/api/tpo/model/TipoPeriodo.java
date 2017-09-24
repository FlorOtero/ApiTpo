package edu.uade.api.tpo.model;

public enum TipoPeriodo {
	periodo1("MENSUAL"),
	periodo2("ANUAL");

	private String tipoPeriodo;
	
	TipoPeriodo(final String tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }

	public String getTipoPeriodo() {
		return tipoPeriodo;
	}

	public void setTipoPeriodo(String tipoPeriodo) {
		this.tipoPeriodo = tipoPeriodo;
	}

	
	
}
