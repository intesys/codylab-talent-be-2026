package it.intesys.codylab.config;

import it.intesys.codylab.db.config.HikariDataSourceProvider;
import it.intesys.codylab.db.repository.ProjectRepository;
import it.intesys.codylab.service.ProjectService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BeanConfiguration {

    @Bean
    public DataSource createDataSource() {
        return HikariDataSourceProvider.getDataSource();
    }

    @Bean
    public ProjectRepository creteProjectRepository(DataSource dataSource) {
        return new ProjectRepository(dataSource);
    }

    @Bean
    public ProjectService createProjectService(ProjectRepository projectRepository) {
        return new ProjectService(projectRepository);
    }
}
