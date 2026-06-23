package it.intesys.codylab.service;

import it.intesys.codylab.db.model.Project;
import it.intesys.codylab.db.repository.ProjectRepository;

import java.util.List;

public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        System.out.println("---- Chiamato costruttore di ProjectService");
        this.projectRepository = projectRepository;
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }
}
