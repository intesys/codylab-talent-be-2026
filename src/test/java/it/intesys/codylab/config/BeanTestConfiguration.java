package it.intesys.codylab.config;

import it.intesys.codylab.db.config.HikariTestDataSourceProvider;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@TestConfiguration
public class BeanTestConfiguration {

    @Bean
    @Primary
    public DataSource dataSource() {
        return HikariTestDataSourceProvider.getDataSource();
    }
}
