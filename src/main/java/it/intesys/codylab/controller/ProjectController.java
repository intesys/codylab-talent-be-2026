package it.intesys.codylab.controller;

import it.intesys.codylab.db.model.Project;
import it.intesys.codylab.service.ProjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        System.out.println("----- Chiamato costruttore ProjectController");
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> findAll(){
        System.out.println("Chiamato findAll");

        return projectService.findAll();
    }
}
