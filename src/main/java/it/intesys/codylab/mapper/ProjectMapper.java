package it.intesys.codylab.mapper;

import it.intesys.codylab.controller.dto.ProjectApiDTO;
import it.intesys.codylab.db.model.Project;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProjectMapper {

    ProjectApiDTO mapTOApiDto(Project project);
    Project mapToEntity(ProjectApiDTO projectApiDTO);
    List<ProjectApiDTO> mapToApiDtoList(List<Project> projectsList);
}
