package it.intesys.codylab.controller;

import it.intesys.codylab.db.model.Activity;
import it.intesys.codylab.db.model.Project;
import it.intesys.codylab.service.ActivityService;
import it.intesys.codylab.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ActivityController {

    /**@RequestMapping("/projects")
    public class ProjectController {

        private final ProjectService projectService;

        public ProjectController(ProjectService projectService) {
            System.out.println("----- Chiamato costruttore ProjectController");
            this.projectService = projectService;
        }

        @GetMapping
        public List<Project> findAll() {
            System.out.println("Chiamato findAll");

            return projectService.findAll();
        }


         * @GetMapping("/{projectId}") public Project findById(@PathVariable("projectId") Long projectId){
         * return projectService.findById(projectId).orElse(null);
         * }
         **/

        /**@GetMapping("/{projectId}")
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


        }
    }**/
        private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/activities")
    public List<Activity> getAllActivities() {
        return activityService.findAll();
    }
}

