package it.intesys.codylab.db.repository;

import it.intesys.codylab.db.config.HikariDataSourceProvider;
import it.intesys.codylab.db.model.Project;
import it.intesys.codylab.db.model.ProjectStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

class ProjectRepositoryIntTest {

    private ProjectRepository projectRepository = new ProjectRepository(HikariDataSourceProvider.getDataSource());

    // 1. Test findAll:restituisca tutti i record presenti nella tabella tracking
    @Test
    void findAll() {
        Optional<Project> project = projectRepository.findAll().stream().findAny();
        assert(project.isPresent());
    }

    //Test findById con l'id  esiste
    @Test
    void testfindById() {
        Optional<Project> project = projectRepository.findById(1L);
        assert(project.isPresent());
    }

    //Test findById con l'id non esiste
    @Test
    void test2findById() {
        Optional<Project> project = projectRepository.findById(100L);
        assert(project.isEmpty());
    }

    @Test
    void insert() {
        Project newProject = new Project();
        newProject.setTitle("New Project")
                .setStatus(ProjectStatus.CREATED)
                .setDescription("New Project")
                .setEstimatedHours(50)
                .setStartDate(LocalDate.now())
                .setCreateDate(LocalDate.now())
                .setEndDate(LocalDate.now().plusDays(30))
                .setUpdateDate(LocalDate.now());

        Long projectId = projectRepository.insert(newProject);
        assert(projectId != null);

        Optional<Project> project = projectRepository.findById(projectId);
        assert(project.isPresent());
    }

    @Test
    void updateProjectById() {
        Project newProject = new Project();
        newProject.setTitle("aggiorna progetto")
                .setStatus(ProjectStatus.CREATED)
                .setDescription("aggiorna progetto")
                .setEstimatedHours(50)
                .setStartDate(LocalDate.now())
                .setEndDate(LocalDate.now().plusDays(30))
                .setUpdateDate(LocalDate.now());

        projectRepository.updateProjectById(1L, newProject);
        Optional<Project> project = projectRepository.findById(1L);
        assert(project.isPresent());
        assert(project.get().getTitle().equals("aggiorna progetto"));

    }

    @Test
    void findAllProjectsWithActivities() {
    }
}