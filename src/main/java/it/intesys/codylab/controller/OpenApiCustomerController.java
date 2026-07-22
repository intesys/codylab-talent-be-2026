package it.intesys.codylab.controller;

import it.intesys.codylab.controller.api.CustomerControllerApi;
import it.intesys.codylab.controller.dto.CustomerApiDTO;
import it.intesys.codylab.controller.dto.ProblemApiDTO;
import it.intesys.codylab.db.model.Customer;
import it.intesys.codylab.mapper.CustomerMapper;
import it.intesys.codylab.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class OpenApiCustomerController implements CustomerControllerApi {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public OpenApiCustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @Override
    public ResponseEntity<List<CustomerApiDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerMapper.mapToApiDtoList(customerService.getAllCustomers()));
    }

    @Override
    public ResponseEntity<CustomerApiDTO> getCustomerById(Long customerId) {
        return customerService.getCustomerById(customerId)
                .map(customer -> ResponseEntity.ok(customerMapper.mapToApiDto(customer)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<CustomerApiDTO> createCustomer(CustomerApiDTO customerApiDTO) {
        var customerData = customerMapper.mapToEntity(customerApiDTO);
        var savedCustomer = customerService.saveCustomer(customerData);
        return ResponseEntity.created(URI.create("/customers/" + savedCustomer.getId()))
                .body(customerMapper.mapToApiDto(savedCustomer));
    }

    @Override
    public ResponseEntity<CustomerApiDTO> updateCustomer(Long customerId, CustomerApiDTO customerApiDTO) {
        return customerService.updateCustomer(customerId, customerMapper.mapToEntity(customerApiDTO))
                .map(customer -> ResponseEntity.ok(customerMapper.mapToApiDto(customer)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(Long customerId) {
        if (customerService.deleteCustomer(customerId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
