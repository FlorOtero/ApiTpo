package edu.uade.api.tpo.dao;

import edu.uade.api.tpo.db.Persistible;

import java.sql.SQLException;

public interface GenericDao<T extends Persistible> {

    public T findById(String id) throws SQLException;

    public void create(T t) throws SQLException;

    public void update(T t) throws SQLException;

    public T findBy(String field, String value) throws SQLException;
    
    public void delete(T t) throws SQLException;

}
