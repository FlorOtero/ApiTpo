package edu.uade.api.tpo.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PersistenceModule {

    private static final Logger logger = LoggerFactory.getLogger(PersistenceModule.class);
    private final DataSource dataSource;
    private static PersistenceModule instance;
    private static final Properties databaseProperties;

    static {
        databaseProperties = new Properties();
        try {
            InputStream input = ClassLoader.getSystemResourceAsStream("database.properties");
            databaseProperties.load(input);
        } catch (IOException e) {
            logger.error("Database properties not found, exiting...", e);
            System.exit(0);
        }
    }

    private PersistenceModule() {

        logger.info("Initializing Datasource...");
        HikariConfig config = new HikariConfig();

        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl(databaseProperties.getProperty("tpo.database.jdbc.url"));
        config.setUsername(databaseProperties.getProperty("tpo.database.jdbc.username"));
        config.setPassword(databaseProperties.getProperty("tpo.database.jdbc.password"));
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
