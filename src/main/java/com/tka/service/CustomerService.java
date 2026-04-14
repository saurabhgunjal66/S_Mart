package com.tka.service;

import java.util.List;
import java.util.Optional; // Import Optional for better null handling

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tka.dao.CustomerDao;
import com.tka.model.Customer;

@Service
public class CustomerService {

    // 1. Use constructor injection for all dependencies (best practice)
    private final CustomerDao customerDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerDao customerDao, PasswordEncoder passwordEncoder) {
        this.customerDao = customerDao;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new customer by encoding their password before saving.
     */
    public boolean addCustomer(Customer customer) {
        // 2. Correct logic: First encode password, then set it, then save.
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);

        // The save method returns the saved entity. Check if it's not null.
        Customer savedCustomer = customerDao.save(customer);
        return savedCustomer != null;
    }

    public List<Customer> getallCustomer() {
        return customerDao.findAll();
    }

    /**
     * Securely verifies a user's login credentials.
     */
    public boolean verifyLogin(String username, String password) {
        // 3. Secure and efficient login verification
        // Find the customer by username from the database
        Optional<Customer> customerOptional = customerDao.findByUsername(username);

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            // Use passwordEncoder.matches() to compare raw password with the stored hash
            return passwordEncoder.matches(password, customer.getPassword());
        }

        // Return false if the user was not found
        return false;
    }
    
    public Customer getCustomerByUsername(String username) {
        // This relies on the findByUsername method in your CustomerDao
        Optional<Customer> customerOptional = customerDao.findByUsername(username);
        // Returns the Customer if found, otherwise returns null
        return customerOptional.orElse(null);
    }
}