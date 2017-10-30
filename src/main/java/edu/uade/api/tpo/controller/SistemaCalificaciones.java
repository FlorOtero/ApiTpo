package edu.uade.api.tpo.controller;

import edu.uade.api.tpo.exceptions.BusinessException;
import edu.uade.api.tpo.model.Calificacion;
import edu.uade.api.tpo.model.EstadoTransaccion;
import edu.uade.api.tpo.model.Transaccion;

import java.util.Date;

public class SistemaCalificaciones {

    private static SistemaCalificaciones instance;

    private SistemaCalificaciones() {

    }

    public static SistemaCalificaciones getInstance() {
        if (instance == null) {
            instance = new SistemaCalificaciones();
        }
        return instance;
    }

    public void calificarTransaccion(Transaccion transaccion, int valor, String obs) throws BusinessException {

        if(transaccion.getEstado() != EstadoTransaccion.A) {
            throw new BusinessException("El estado de la transaccion debe ser aprobada");
        }

        Calificacion calificacion = new Calificacion();
        calificacion.setCalificacion(valor);
        calificacion.setObservaciones(obs);
        calificacion.setFecha(new Date());
        transaccion.setCalificacion(calificacion);
        SistemaTransacciones.getInstance().actualizarTransaccion(transaccion);

    }

}
