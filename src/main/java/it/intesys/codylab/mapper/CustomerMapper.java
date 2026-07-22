package it.intesys.codylab.mapper;

import it.intesys.codylab.controller.dto.CustomerApiDTO;
import it.intesys.codylab.db.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {


    CustomerApiDTO toDto(Customer entity);


    Customer toEntity(CustomerApiDTO dto);


    List<CustomerApiDTO> toDtoList(List<Customer> entityList);


    void updateEntityFromDto(CustomerApiDTO dto, @MappingTarget Customer entity);
}