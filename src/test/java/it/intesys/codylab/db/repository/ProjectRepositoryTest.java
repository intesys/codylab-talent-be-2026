package it.intesys.codylab.db.repository;

import it.intesys.codylab.db.config.HikariTestDataSourceProvider;
import it.intesys.codylab.db.model.Project;
import it.intesys.codylab.db.model.ProjectStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectRepositoryTest {

    private ProjectRepository projectRepository = new ProjectRepository(HikariTestDataSourceProvider.getDataSource());


    @Test
    void findAll() {
        List<Project> projects = projectRepository.findAll();
        assertNotNull(projects, "La lista non deve essere nulla");
        assertFalse(projects.isEmpty(), "La lista deve contenere almeno un record.");
    }

    @Test
    void findById() {
        Project project = projectRepository.findById(1L).orElse(null);
        assertNotNull(project, "Dovrebbe esistere un progetto con ID 1.");
        assertEquals(1L, project.getId(), "L'ID restituito dovrebbe essere 1.");
    }

    @Test
    void insert() {
        Project p =  new Project();
        p.setTitle("Test project");
        p.setDescription("Test Descrizione di prova project");
        p.setEstimatedHours(2);
        p.setStatus(ProjectStatus.WORKING);
        p.setStartDate(LocalDate.now());
        p.setEndDate(LocalDate.now());
        p.setCreateDate(LocalDate.now());

        Long id = projectRepository.insert(p);

        assertNotNull(id, "L'Id non deve essere nullo");
        assertTrue(projectRepository.findById(id).isPresent(),"Il record dovrebbe essere presente nel database");

    }

    @Test
    void updateProjectById() {
        Project p = projectRepository.findById(1L).orElse(null);
        p.setTitle("Test project: titolo aggiornato");

        projectRepository.updateProjectById(1L, p);
        Project updated = projectRepository.findById(1L).get();
        assertEquals("Test project: titolo aggiornato", updated.getTitle(), "Il titolo dovrebbe essere stato aggiornato");
    }

    //@Test
    void deleteProjectById() {
        assertTrue(projectRepository.findById(1L).isPresent());
        projectRepository.deleteById(1L);
        assertFalse(projectRepository.findById(1L).isPresent(), "Il progetto avrebbe dovuto essere cancellato.");

    }



    @Test
    void findAllProjectsWithActivities() {
        List<Project> projects = projectRepository.findAllProjectsWithActivities();
        assertNotNull(projects, "La lista non deve essere nulla");
    }

    @Test
    void countProjectsInProgressByClient() {
    }
}