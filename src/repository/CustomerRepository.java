package repository;

import domain.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepository {
    Map<String, Customer> customerById = new HashMap<>();

    public List<Customer> findAll() {
        return new ArrayList<>(customerById.values());
    }

    public void save(Customer customer) {
        customerById.put(customer.getId(), customer);
    }
}
