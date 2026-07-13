package it.intesys.codylab.db.repository;

import it.intesys.codylab.db.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {

    private final List<Customer> customers = new ArrayList<>();

    // --- Flusso lettura (Dania) ---

    public List<Customer> findAll() {
        return customers;
    }

    public Optional<Customer> findById(Long id) {
        return customers.stream()
                .filter(c -> c.getId() != null && c.getId().equals(id))
                .findFirst();
    }

    // --- Flusso scritta (Michele) ---

    public Customer save(Customer customer) {
        return null;
    }

    public Optional<Customer> update(Long id, Customer customerDetails) {
        return Optional.empty();
    }

    public boolean deleteById(Long id) {
        return false;
    }
}