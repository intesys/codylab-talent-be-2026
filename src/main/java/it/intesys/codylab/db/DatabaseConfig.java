package it.intesys.codylab.db;

/**
 * Configurazione DB letta da variabili ambiente con fallback di default.
 */
public final class DatabaseConfig {

    private final String jdbcUrl;
    private final String username;
    private final String password;
    private final int maximumPoolSize;

    public DatabaseConfig(String jdbcUrl, String username, String password, int maximumPoolSize) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        this.maximumPoolSize = maximumPoolSize;
    }

    public static DatabaseConfig getConfig() {
        return new DatabaseConfig(
                "jdbc:postgresql://127.0.0.1:55432/codylab",
                "postgres",
                "postgres",
                8
        );
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }
}



