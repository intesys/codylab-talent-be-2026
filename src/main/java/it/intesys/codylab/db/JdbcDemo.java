package it.intesys.codylab.db;

import it.intesys.codylab.db.config.HikariDataSourceProvider;
import it.intesys.codylab.db.model.Activity;
import it.intesys.codylab.db.model.Project;
import it.intesys.codylab.db.repository.ActivitiesRepository;
import it.intesys.codylab.db.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public  Project getProjectById(long id) {
        DataSource dataSource = HikariDataSourceProvider.getDataSource();
        ProjectRepository projectRepository = new ProjectRepository(dataSource);
        Optional<Project> project = projectRepository.findById(id);
        project.ifPresent(p -> logger.info(p.toString()));
        return project.orElse(null);

    }

    public void insertProject(Project project) {
        DataSource dataSource = HikariDataSourceProvider.getDataSource();
        ProjectRepository projectRepository = new ProjectRepository(dataSource);
        long id = projectRepository.insert(project);
        logger.info("Inserted project with id = " + id);
    }


    public void updateProjectById(Project project, long id) {

        DataSource dataSource = HikariDataSourceProvider.getDataSource();
        ProjectRepository projectRepository = new ProjectRepository(dataSource);

        Project projectToUpdate = getProjectById(id);

        projectToUpdate.setTitle(project.getTitle());
        projectToUpdate.setDescription(project.getDescription());
        projectToUpdate.setEstimatedHours(project.getEstimatedHours());
        projectToUpdate.setStatus(project.getStatus());
        projectToUpdate.setStartDate(project.getStartDate());
        projectToUpdate.setEndDate(project.getEndDate());
        projectToUpdate.setUpdateDate(project.getUpdateDate());

        long updatedId = projectRepository.update(projectToUpdate);
        logger.info("Updated project with id = " + id);
    }

    public void getAllActivies() {
        DataSource dataSource = HikariDataSourceProvider.getDataSource();
        ActivitiesRepository activitiesRepository = new ActivitiesRepository(dataSource);

        List<Activity> lista = activitiesRepository.findAll();

        lista.stream().forEach(e -> logger.info(e.toString()));

    }

    public void findAllActivitiesWorkingProjects() {
        DataSource dataSource = HikariDataSourceProvider.getDataSource();
        ActivitiesRepository activitiesRepository = new ActivitiesRepository(dataSource);

        List<Activity> lista = activitiesRepository.findAllActivitiesWorkingProjects();

        lista.stream().forEach(e -> logger.info(e.toString()));

    }

    public void countProjectsInProgressByClient(long id){
        DataSource dataSource = HikariDataSourceProvider.getDataSource();
        ProjectRepository projectRepository = new ProjectRepository(dataSource);

        Map<String, Integer> result = projectRepository.countProjectsInProgressByClient(id);
        result.forEach((name, count) ->
                logger.info("Cliente: " + name + " - Working: " + count)
        );
    }

    public void CustomerProjects(){
        DataSource dataSource = HikariDataSourceProvider.getDataSource();
        ProjectRepository projectRepository = new ProjectRepository(dataSource);

        Map<String, Integer> result = projectRepository.CustomerProjects();
        result.forEach((name, count) ->
                logger.info("Cliente: " + name + " ha: " + count + "progetti")
        );
    }
}











