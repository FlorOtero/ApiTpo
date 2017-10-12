package edu.uade.api.tpo.dao.impl;

import edu.uade.api.tpo.dao.GenericDao;
import edu.uade.api.tpo.model.Articulo;
import edu.uade.api.tpo.model.Producto;
import edu.uade.api.tpo.model.Servicio;

import java.sql.SQLException;

public class ArticuloDaoImpl implements GenericDao<Articulo> {

	private static GenericDao<Articulo> instance;

	private ArticuloDaoImpl() {

	}

	public static GenericDao<Articulo> getInstance() {
		if (instance == null) {
			instance = new ArticuloDaoImpl();
		}
		return instance;
	}

	@Override
	public Articulo findById(String id) throws SQLException {
		Articulo articulo = ProductoDaoImpl.getInstance().findById(id);
		if (articulo == null) {
			articulo = ServicioDaoImpl.getInstance().findById(id);
		}
		return articulo;
	}

	@Override
	public void create(Articulo articulo) throws SQLException {
		if (articulo instanceof Producto) {
			ProductoDaoImpl.getInstance().create((Producto) articulo);
		} else if (articulo instanceof Servicio) {
			ServicioDaoImpl.getInstance().create((Servicio) articulo);
		}
	}

	@Override
	public void update(Articulo articulo) throws SQLException {
		if (articulo instanceof Producto) {
			ProductoDaoImpl.getInstance().update((Producto) articulo);
		} else if (articulo instanceof Servicio) {
			ServicioDaoImpl.getInstance().update((Servicio) articulo);
		}
	}

	@Override
	public Articulo findBy(String field, String value) throws SQLException {
		throw new UnsupportedOperationException("Find by is not supported on class Articulo!");
	}

	@Override
	public void delete(Articulo t) throws SQLException {
		if (t instanceof Producto) {
			ProductoDaoImpl.getInstance().delete((Producto) t);
		} else if (t instanceof Servicio) {
			ServicioDaoImpl.getInstance().delete((Servicio) t);
		}
	}
}
