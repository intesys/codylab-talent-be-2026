package it.intesys.codylab.db.repository;

import it.intesys.codylab.db.config.HikariDataSourceProvider;
import it.intesys.codylab.db.model.Project;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class ProjectRepositoryTest {
    private ProjectRepository projectRepository = new ProjectRepository(HikariDataSourceProvider.getDataSource());

    @Test
    void findAll() {
        Optional<Project> project = projectRepository.findAll().stream().findAny();
        assert(project.isPresent());
    }

    @Test
    void findById() {
        Optional<Project> project = projectRepository.findById(1L);
        assert(project.isPresent());
    }

    @Test
    void insert() {
        Project newProject = new Project();
        newProject.setTitle("Nuovo Progetto di Test");
        newProject.setDescription("Nuovo Progetto di Test");
        newProject.setEstimatedHours(2);

        newProject.setStartDate(LocalDate.now());
        newProject.setEndDate(LocalDate.now().plusDays(30));
        newProject.setUpdateDate(LocalDate.now());

        Optional<Project> project = projectRepository.findById(10L);
        assertTrue(project.isPresent());
    }

    //@Test
//    void updateProjectById() {
//        Optional<Project> project = projectRepository.updateProjectById(1L);
//        assert(project.isPresent());
//    }
}