package it.intesys.codylab.controller;

import it.intesys.codylab.controller.api.CustomerControllerApi;
import it.intesys.codylab.controller.dto.CustomerApiDTO;
import it.intesys.codylab.mapper.CustomerMapper;
import it.intesys.codylab.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController implements CustomerControllerApi {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    // 1. GET ALL
    @Override
    public ResponseEntity<List<CustomerApiDTO>> getAllCustomers() {
        var entities = customerService.getAllCustomers();
        List<CustomerApiDTO> dtos = customerMapper.toDtoList(entities);
        return ResponseEntity.ok(dtos);
    }

    // 2. GET BY ID
    @Override
    public ResponseEntity<CustomerApiDTO> getCustomerById(Long id) {
        return customerService.getCustomerById(id)
                .map(customerMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. POST (Create)
    @Override
    public ResponseEntity<CustomerApiDTO> createCustomer(CustomerApiDTO customerApiDTO) {
        var entityToSave = customerMapper.toEntity(customerApiDTO);
        var savedEntity = customerService.saveCustomer(entityToSave);
        CustomerApiDTO responseDto = customerMapper.toDto(savedEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 4. PUT (Update)
    @Override
    public ResponseEntity<CustomerApiDTO> updateCustomer(Long id, CustomerApiDTO customerApiDTO) {
        var entityDetails = customerMapper.toEntity(customerApiDTO);
        return customerService.updateCustomer(id, entityDetails)
                .map(customerMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. DELETE
    @Override
    public ResponseEntity<Void> deleteCustomer(Long id) {
        if (customerService.deleteCustomer(id)) {
            return ResponseEntity.noContent().build(); // HTTP 204 No Content
        }
        return ResponseEntity.notFound().build(); // HTTP 404 Not Found
    }
}