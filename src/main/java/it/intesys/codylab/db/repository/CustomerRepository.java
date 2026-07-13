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
        // Calcola il prossimo ID disponibile in modo dinamico
        long nextId = customers.stream()
                .mapToLong(c -> c.getId() != null ? c.getId() : 0L)
                .max()
                .orElse(0L) + 1;

        customer.setId(nextId);
        customers.add(customer);
        return customer;
    }

    public Optional<Customer> update(Long id, Customer customerDetails) {
        return findById(id).map(existingCustomer -> {
            // Aggiorna i campi del cliente esistente con i nuovi dettagli
            existingCustomer.setName(customerDetails.getName());
            // Aggiungi qui altri eventuali setter se la classe Customer ha altri campi (es. email)
            return existingCustomer;
        });
    }

    public boolean deleteById(Long id) {
        return customers.removeIf(c -> c.getId() != null && c.getId().equals(id));
    }
}