package it.intesys.codylab.mapper;

import it.intesys.codylab.controller.dto.CustomerApiDTO;
import it.intesys.codylab.db.model.Customer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomerMapper {

    CustomerApiDTO mapToApiDto(Customer customer);
    Customer mapToEntity(CustomerApiDTO customerApiDTO);
    List<CustomerApiDTO> mapToApiDtoList(List<Customer> customersList);
}
