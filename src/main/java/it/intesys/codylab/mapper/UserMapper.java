package it.intesys.codylab.mapper;

import it.intesys.codylab.controller.dto.UserApiDTO;
import it.intesys.codylab.db.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    @Mapping(target = "firstName", source = "name")
    @Mapping(target = "lastName", source = "surname")
    @Mapping(target = "accessToken", ignore = true)
    UserApiDTO mapToApiDto(User user);
}
