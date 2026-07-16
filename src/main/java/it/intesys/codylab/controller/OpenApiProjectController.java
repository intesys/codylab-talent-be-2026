package it.intesys.codylab.controller;

import it.intesys.codylab.controller.api.ProjectControllerApi;
import it.intesys.codylab.controller.dto.ProjectApiDTO;
import it.intesys.codylab.db.model.Project;
import it.intesys.codylab.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class OpenApiProjectController implements ProjectControllerApi {

    private final ProjectService projectService;

    public OpenApiProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public ResponseEntity<ProjectApiDTO> getProjectById(Long projectId) {
        Optional<Project> projectOptional = projectService.findById(projectId);
        if (projectOptional.isPresent()) {
            System.out.println("Project " + projectId + " trovato");
            Project project = projectOptional.get();
            ProjectApiDTO result = new ProjectApiDTO()
                    .id(project.getId())
                    .title(project.getTitle())
                    .description(project.getDescription())
                    .createDate(project.getCreateDate())
                    .status(ProjectApiDTO.StatusEnum.valueOf(project.getStatus().name()))
                    .endDate(project.getEndDate())
                    .estimatedHours(project.getEstimatedHours());
            return ResponseEntity.ok(result);
        } else {
            System.out.println("Project " + projectId + " non trovato");
            return ResponseEntity.notFound().build();
        }
    }
}
