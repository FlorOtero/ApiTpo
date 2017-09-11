package edu.uade.api.tpo.dao;

import java.io.Serializable;
import java.sql.SQLException;

public interface BaseDao<T extends Serializable> {

    public T findById(String id) throws SQLException;

    public void create(T t) throws SQLException;

    public void update(T t) throws SQLException;

}
