package edu.uade.api.tpo.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface ManyToOneDao<T extends Serializable> extends GenericDao<T> {

    public List<T> findManyBy(String field, String value) throws SQLException;

}
