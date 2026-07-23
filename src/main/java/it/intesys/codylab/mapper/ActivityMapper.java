package it.intesys.codylab.mapper;

import it.intesys.codylab.controller.dto.ActivityApiDTO;
import it.intesys.codylab.db.model.Activity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ActivityMapper {

    ActivityApiDTO mapToApiDto(Activity activity);

    Activity mapToEntity(ActivityApiDTO activityApiDTO);

    List<ActivityApiDTO> mapToApiDtoList(List<Activity> activitiesList);
}