package it.intesys.codylab.controller;

import it.intesys.codylab.db.model.Project;
import it.intesys.codylab.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        System.out.println("----- Chiamato costruttore ProjectController");
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<Project>> findAll() {
        System.out.println("Chiamato findAll");

        return ResponseEntity.ok(projectService.findAll());
    }

    /**
     * @GetMapping("/{projectId}") public Project findById(@PathVariable("projectId") Long projectId){
     * return projectService.findById(projectId).orElse(null);
     * }
     **/

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> findById(@PathVariable("projectId") Long projectId) {
        Optional<Project> projectOptional = projectService.findById(projectId);
        if (projectOptional.isPresent()) {
            System.out.println("Project " + projectId + " trovato");
            return ResponseEntity.ok(projectOptional.get());
        } else {
            System.out.println("Project " + projectId + " non trovato");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Long> insert(@RequestBody Project projectData) {
        long projectId = projectService.insert(projectData);
        System.out.println("Project " + projectId + " inserito");
        return ResponseEntity.created(URI.create("/projects/" + projectId))
                .body(projectId);
    }
}
