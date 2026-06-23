package it.intesys.codylab.controller;

import it.intesys.codylab.db.config.HikariDataSourceProvider;
import it.intesys.codylab.db.model.Project;
import it.intesys.codylab.db.repository.ProjectRepository;
import it.intesys.codylab.service.ProjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService =
            new ProjectService(new ProjectRepository(HikariDataSourceProvider.getDataSource()));

    @GetMapping
    public List<Project> findAll(){
        System.out.println("Chiamato findAll");

        return projectService.findAll();
    }
}
