package edu.uade.api.tpo.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractManyToOneDao<T extends Serializable> extends AbstractDao<T> implements ManyToOneDao<T> {

    @Override
    public List<T> findManyBy(String field, String value) throws SQLException {
        try (Connection conn = this.getConnection(); PreparedStatement ps = findManyBy(field, value, conn); ResultSet rs = ps.executeQuery()) {
            List<T> result = mapMany(rs);
            conn.commit();
            return result;
        }
    }

    public abstract PreparedStatement findManyBy(String field, String value, Connection conn) throws SQLException;

    public abstract List<T> mapMany(ResultSet rs) throws SQLException;

}
