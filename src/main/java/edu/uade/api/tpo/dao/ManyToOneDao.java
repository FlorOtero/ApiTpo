package edu.uade.api.tpo.dao;

import edu.uade.api.tpo.db.Persistible;

import java.sql.SQLException;
import java.util.List;

public interface ManyToOneDao<T extends Persistible> extends GenericDao<T> {

    public List<T> findManyBy(String field, String value) throws SQLException;

}
