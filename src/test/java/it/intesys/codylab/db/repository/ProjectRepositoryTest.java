package it.intesys.codylab.db.repository;

import it.intesys.codylab.db.config.HikariDataSourceProvider;
import it.intesys.codylab.db.model.Project;
import it.intesys.codylab.db.model.ProjectStatus;
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
        newProject.setTitle("Nuovo Progetto di Test")
                        .setStatus(ProjectStatus.CREATED)
                .setDescription("Nuovo Progetto di Test")
                .setEstimatedHours(2)
                .setStartDate(LocalDate.now())
                .setEndDate(LocalDate.now().plusDays(30))
                .setUpdateDate(LocalDate.now())
                .setCreateDate(LocalDate.now());

        Long projectId = projectRepository.insert(newProject);
        assert(projectId != null);

        Optional<Project> savedProject = projectRepository.findById(projectId);
        assert(savedProject.isPresent());
    }

    @Test
    void updateProjectById() {
        Project newProject = new Project();
        newProject.setTitle("aggiornato Progetto di Test")
                .setStatus(ProjectStatus.CREATED)
                .setDescription("aggiornato Progetto di Test")
                .setEstimatedHours(2)
                .setStartDate(LocalDate.now())
                .setEndDate(LocalDate.now().plusDays(30))
                .setUpdateDate(LocalDate.now());


         projectRepository.updateProjectById(1L, newProject);
        Optional<Project> savedProject = projectRepository.findById(1L);
        assert(savedProject.isPresent());
        assert ("aggiornato Progetto di Test".equals(savedProject.get().getTitle()));
        //assert ("aggiornato Progetto di Test" == savedProject.get().getTitle());
    }
}