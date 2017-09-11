package edu.uade.api.tpo.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class PersistenceModule {

    private static final Logger logger = LoggerFactory.getLogger(PersistenceModule.class);
    private final DataSource dataSource;
    private static PersistenceModule instance;

    private PersistenceModule() {
        logger.info("Initializing Datasource...");
        HikariConfig config = new HikariConfig();

        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://api-tpo.ck6ywww2iqip.sa-east-1.rds.amazonaws.com:3306/apitpo");
        config.setUsername("master");
        config.setPassword("vWAJjK7CJgKAR97H");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("useLocalTransactionState", "true");
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("cacheResultSetMetadata", "true");
        config.addDataSourceProperty("cacheServerConfiguration", "true");
        config.addDataSourceProperty("elideSetAutoCommits", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");
        config.addDataSourceProperty("serverTimezone", "GMT-3");
        config.setMinimumIdle(5);
        config.setMaximumPoolSize(10);
        config.setConnectionTimeout(5000);
        config.setIdleTimeout(30000);

        this.dataSource = new HikariDataSource(config);
        logger.info("Datasource successfully initialized");
    }

    public static PersistenceModule getInstance() {
        if (instance == null) {
            instance = new PersistenceModule();
        }
        return instance;
    }

    public DataSource getDataSource() {
        return this.dataSource;
    }
}
