package it.intesys.codylab.controller;

import it.intesys.codylab.controller.api.ProjectControllerApi;
import it.intesys.codylab.controller.dto.ProjectApiDTO;
import it.intesys.codylab.db.model.Project;
import it.intesys.codylab.mapper.ProjectMapper;
import it.intesys.codylab.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class OpenApiProjectController implements ProjectControllerApi {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    public OpenApiProjectController(ProjectService projectService, ProjectMapper projectMapper) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
    }

    @Override
    public ResponseEntity<List<ProjectApiDTO>> getAllProjects() {
        return ResponseEntity.ok(projectMapper.mapToApiDtoList(projectService.findAll()));
    }

    @Override
    public ResponseEntity<ProjectApiDTO> getProjectById(Long projectId) {
        Optional<Project> projectOptional = projectService.findById(projectId);
        if (projectOptional.isPresent()) {
            System.out.println("Project " + projectId + " trovato");
            Project project = projectOptional.get();
            return ResponseEntity.ok(projectMapper.mapTOApiDto(project));
        } else {
            System.out.println("Project " + projectId + " non trovato");
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<ProjectApiDTO> createProject(ProjectApiDTO projectApiDTO) {
        Project projectData = projectMapper.mapToEntity(projectApiDTO);
        projectData.setCreateDate(LocalDate.now());
        long projectId = projectService.insert(projectData);
        System.out.println("Project " + projectId + " inserito");
        return ResponseEntity.ok(projectMapper.mapTOApiDto(projectService.findById(projectId).get()));
    }
}
