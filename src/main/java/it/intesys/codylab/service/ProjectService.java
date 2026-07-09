package it.intesys.codylab.service;

import it.intesys.codylab.db.model.Project;
import it.intesys.codylab.db.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;

public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        System.out.println("---- Chiamato costruttore di ProjectService");
        this.projectRepository = projectRepository;
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Optional<Project> findById(Long projectId) {
        return projectRepository.findById(projectId);
    }

    public long insert(Project project) {
        return projectRepository.insert(project);
    }
}
