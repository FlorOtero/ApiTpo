package edu.uade.api.tpo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import edu.uade.api.tpo.db.PersistenceModule;
import edu.uade.api.tpo.db.Persistible;
import edu.uade.api.tpo.util.UUIDUtils;

public abstract class AbstractDao<T extends Persistible> implements GenericDao<T> {

    private DataSource dataSource;
    protected String schema = "apitpo";

    public AbstractDao() {
        this.dataSource = PersistenceModule.getInstance().getDataSource();
    }

    @Override
    public void create(T t) throws SQLException {
        t.setId(UUIDUtils.generate());
        try (Connection conn = this.getConnection(); PreparedStatement ps = create(t, conn)) {
            ps.execute();
        }
        
        doAfterCreate(t);
    }

    @Override
    public final T findById(String id) throws SQLException {
        try (Connection conn = this.getConnection(); PreparedStatement ps = findById(id, conn); ResultSet rs = ps.executeQuery()) {
            T t = map(rs);
            return t;
        }
    }

    @Override
    public final void update(T t) throws SQLException {
        try (Connection conn = this.getConnection(); PreparedStatement ps = update(t, conn)) {
            ps.executeUpdate();
        }
    }

    @Override
    public T findBy(String field, String value) throws SQLException {
        try (Connection conn = this.getConnection(); PreparedStatement ps = findBy(field, value, conn); ResultSet rs = ps.executeQuery()) {
            T t = map(rs);
            return t;
        }
    }
    
    public void doAfterCreate(T t) throws SQLException {

    }

    protected Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

    public abstract PreparedStatement create(T t, Connection conn) throws SQLException;

    public abstract PreparedStatement findById(String id, Connection conn) throws SQLException;

    public abstract T map(ResultSet rs) throws SQLException;

    public abstract PreparedStatement update(T t, Connection conn) throws SQLException;

    public abstract PreparedStatement findBy(String field, String value, Connection conn) throws SQLException;

}
