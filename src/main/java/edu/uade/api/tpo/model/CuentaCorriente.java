package edu.uade.api.tpo.model;

import java.util.ArrayList;
import java.util.List;

public class CuentaCorriente {
	
	private float saldo;
	private List<Transaccion> transacciones;
	
	public CuentaCorriente() {
		super();
		this.saldo = 0; //cuando arranca tiene la cc sin saldo, hasta que empiezan las transacciones
		this.transacciones = new ArrayList<Transaccion>();
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public List<Transaccion> getTransacciones() {
		return transacciones;
	}

	public Transaccion getTransaccion(int index){
		return transacciones.get(index);
	}
	
	public void setTransaccion(Transaccion t){
		transacciones.add(t);
	}	
	
	public void removeTransaccion(int index){
		transacciones.remove(index);
	}	
	
	public boolean isEmptyTransacciones(){
		return transacciones.isEmpty();
	}
	
	public boolean existeTransaccion(Transaccion t) {
		return transacciones.contains(t);
	}
	

}
