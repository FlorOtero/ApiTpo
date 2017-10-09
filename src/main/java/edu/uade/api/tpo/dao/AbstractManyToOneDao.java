package edu.uade.api.tpo.dao;

import edu.uade.api.tpo.db.Persistible;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractManyToOneDao<T extends Persistible> extends AbstractDao<T> implements ManyToOneDao<T> {

    @Override
    public List<T> findManyBy(String field, String value) throws SQLException {
        try (Connection conn = this.getConnection(); PreparedStatement ps = findManyBy(field, value, conn); ResultSet rs = ps.executeQuery()) {
            List<T> result = mapMany(rs);
            return result;
        }
    }
    
    @Override
    public List<T> findManyLike(String field, String value) throws SQLException {
        try (Connection conn = this.getConnection(); PreparedStatement ps = findManyLike(field, value, conn); ResultSet rs = ps.executeQuery()) {
            List<T> result = mapMany(rs);
            return result;
        }
    }

    public abstract PreparedStatement findManyBy(String field, String value, Connection conn) throws SQLException;
    
    public abstract PreparedStatement findManyLike(String field, String value, Connection conn) throws SQLException;

    public abstract List<T> mapMany(ResultSet rs) throws SQLException;

}
