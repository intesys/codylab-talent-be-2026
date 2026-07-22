package it.intesys.codylab.mapper;

import it.intesys.codylab.controller.dto.TrackingApiDTO;
import it.intesys.codylab.db.model.Tracking;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TrackingMapper {

    TrackingApiDTO mapToApiDto(Tracking tracking);
    Tracking mapToEntity(TrackingApiDTO trackingApiDTO);
    List<TrackingApiDTO> mapToApiDtoList(List<Tracking> trackingsList);
}
