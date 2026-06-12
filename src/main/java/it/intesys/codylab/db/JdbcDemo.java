package it.intesys.codylab.db;

import it.intesys.codylab.db.config.HikariDataSourceProvider;
import it.intesys.codylab.db.model.Project;
import it.intesys.codylab.db.repository.ProjectRepository;
import it.intesys.codylab.db.repository.ActivityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Mini-runner per testare JDBC + Hikari sulle tabelle prodotto/libro.
 */
public class JdbcDemo {

    private static final Logger logger = LoggerFactory.getLogger(JdbcDemo.class);

    public void getAllProjects() {
        DataSource dataSource = HikariDataSourceProvider.getDataSource();

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM projects");
                ResultSet rs = statement.executeQuery()
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

    public void getProjectById(long id) {
        DataSource dataSource = HikariDataSourceProvider.getDataSource();
        ProjectRepository projectRepository = new ProjectRepository(dataSource);
        Optional<Project> project = projectRepository.findById(id);
        project.ifPresent(p -> logger.info(p.toString()));
    }

    public void insertProject(Project project) {
        DataSource dataSource = HikariDataSourceProvider.getDataSource();
        ProjectRepository projectRepository = new ProjectRepository(dataSource);
        long id = projectRepository.insert(project);
        logger.info("Inserted project with id = " + id);
    }

    // Metodo per testare l'aggiornamento di un progetto tramite il suo ID sul repository
    public void updateProjectById(long id, Project project) {
        DataSource dataSource = HikariDataSourceProvider.getDataSource();
        ProjectRepository projectRepository = new ProjectRepository(dataSource);
        projectRepository.updateProjectById(id, project);
        logger.info("Updated project with id = " + id);
    }


    public void getAllActivitiesFromRepository() {
        DataSource dataSource = HikariDataSourceProvider.getDataSource();
        ActivityRepository activityRepository = new ActivityRepository(dataSource);
        activityRepository.findAll().forEach(a -> logger.info(a.getName()));
    }
}

