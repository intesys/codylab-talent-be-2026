package it.intesys.codylab.db;

import it.intesys.codylab.db.config.HikariDataSourceProvider;
import it.intesys.codylab.db.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Mini-runner per testare JDBC + Hikari sulle tabelle prodotto/libro.
 */
public class JdbcDemo {

    private static final Logger logger = LoggerFactory.getLogger(JdbcDemo.class);

    public void getAllProjects() {
        DataSource dataSource = HikariDataSourceProvider.getDataSource();

        try (
                var connection = dataSource.getConnection();
                var statement = connection.prepareStatement("SELECT * FROM projects");
                var rs = statement.executeQuery()
        ) {
            while (rs.next()) {
                logger.info(rs.getString("title"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        HikariDataSourceProvider.shutdown();
    }

    public void getAllProjectsFromRepository() {
        DataSource dataSource = HikariDataSourceProvider.getDataSource();
        ProjectRepository projectRepository = new ProjectRepository(dataSource);
        projectRepository.findAll().forEach(p -> logger.info(p.getTitle()));
    }
}

