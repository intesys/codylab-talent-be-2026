package it.intesys.codylab.db.repository;

import it.intesys.codylab.db.model.Customer;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {

    private final List<Customer> customers = new ArrayList<>();

    public List<Customer> findAll() {
        return customers;
    }

    public Optional<Customer> findById(Long id) {
        return customers.stream()
                .filter(c -> c.getId() != null && c.getId().equals(id))
                .findFirst();
    }

    public Customer save(Customer customer) {
        long nextId = customers.stream()
                .mapToLong(c -> c.getId() != null ? c.getId() : 0L)
                .max()
                .orElse(0L) + 1;

        customer.setId(nextId);
        if (customer.getCreateDate() == null) {
            customer.setCreateDate(LocalDate.now());
        }
        customer.setUpdateDate(LocalDate.now());

        customers.add(customer);
        return customer;
    }

    public Optional<Customer> update(Long id, Customer customerDetails) {
        return findById(id).map(existingCustomer -> {
            existingCustomer.setName(customerDetails.getName());
            existingCustomer.setUpdateDate(LocalDate.now());
            return existingCustomer;
        });
    }

    public boolean deleteById(Long id) {
        return customers.removeIf(c -> c.getId() != null && c.getId().equals(id));
    }
}