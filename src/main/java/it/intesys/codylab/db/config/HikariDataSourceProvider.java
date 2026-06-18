package it.intesys.codylab.db.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

/**
 * Singleton lazy del DataSource con HikariCP.
 */
public final class HikariDataSourceProvider {

    private HikariDataSourceProvider() {
    }

    private static final class DataSourceHolder {
        private static final HikariDataSource dataSource = buildDataSource(DatabaseConfig.getConfig());
    }

    public static DataSource getDataSource() {
        return DataSourceHolder.dataSource;
    }

    private static HikariDataSource buildDataSource(DatabaseConfig databaseConfig) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(databaseConfig.getJdbcUrl());
        hikariConfig.setUsername(databaseConfig.getUsername());
        hikariConfig.setPassword(databaseConfig.getPassword());
        hikariConfig.setMaximumPoolSize(databaseConfig.getMaximumPoolSize());
        hikariConfig.setMinimumIdle(1);
        hikariConfig.setPoolName("codylab-hikari");
        hikariConfig.setConnectionTimeout(10_000);
        hikariConfig.setIdleTimeout(60_000);
        hikariConfig.setMaxLifetime(600_000);

        return new HikariDataSource(hikariConfig);
    }

    public static void shutdown() {
        DataSourceHolder.dataSource.close();
    }
}



