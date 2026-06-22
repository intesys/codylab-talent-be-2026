package it.intesys.codylab.db.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class HikariTestDataSourceProvider {

    private static final DataSource datasouce = buildDataSource(getConfig());

    public static DataSource getDataSource() {
        return datasouce;
    }

    public static DatabaseConfig getConfig() {
        return new DatabaseConfig(
                "jdbc:h2:mem:codylab2026;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'classpath:sql/esercizio-1.sql'",
                "codylab2026",
                "codylab2026",
                2
        );
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
}
