package it.intesys.codylab.db;

import it.intesys.codylab.db.config.HikariDataSourceProvider;
import it.intesys.codylab.db.model.Project;
import it.intesys.codylab.db.model.Tracking;
import it.intesys.codylab.db.repository.ProjectRepository;
import it.intesys.codylab.db.repository.TrackingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Runner class to test JDBC + Hikari on projects and tracking tables.
 */
public class JdbcDemo {

    private static final Logger logger = LoggerFactory.getLogger(JdbcDemo.class);

    // Main method to run the tests
    public static void main(String[] args) {
        DataSource dataSource = HikariDataSourceProvider.getDataSource();
        TrackingRepository trackingRepository = new TrackingRepository(dataSource);

        logger.info("--- Starting Timesheet (Trackings) Test Execution ---");

        // 1. Insert sample tracking logs (Mario and Sara scenarios)
        Tracking marioLog = new Tracking()
                .setDescription("Implemented authentication APIs")
                .setDurationMinutes(90)
                .setActivityId(2) // Sviluppo backend CRM
                .setUserId(1)     // Mario Rossi
                .setCreateDate(LocalDate.now());

        Tracking saraLog = new Tracking()
                .setDescription("Verified edge cases for the login form")
                .setDurationMinutes(120)
                .setActivityId(4) // Testing CRM
                .setUserId(4)     // Sara Neri
                .setCreateDate(LocalDate.now());

        long id1 = trackingRepository.insert(marioLog);
        long id2 = trackingRepository.insert(saraLog);
        logger.info("Successfully inserted tracking records with IDs: {} and {}", id1, id2);

        // 2. Scenario 1: Total minutes logged by Mario (User ID 1)
        int marioTotalTime = trackingRepository.getTotalMinutesByUserId(1);
        logger.info("Result Scenario 1 -> Total minutes logged by Mario (User ID 1): {} minutes", marioTotalTime);

        // 3. Scenario 2: Total minutes spent on Activity ID 2
        int activityTotalTime = trackingRepository.getTotalMinutesByActivityId(2);
        logger.info("Result Scenario 2 -> Total minutes spent on Activity ID 2: {} minutes", activityTotalTime);

        // 4. Scenario 3: Work summary for Activity ID 4
        List<String> workSummary = trackingRepository.getUsersWorkSummaryByActivityId(4);
        logger.info("Result Scenario 3 -> Work summary for Activity ID 4:");
        workSummary.forEach(row -> logger.info(" - {}", row));

        logger.info("--- Timesheet Test Execution Completed Successfully ---");
        HikariDataSourceProvider.shutdown();
    }

    // Existing project methods retained for reference
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
}