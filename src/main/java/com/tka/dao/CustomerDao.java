package com.tka.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tka.model.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer> {

    // Add this method: Spring will generate the SQL to find a customer by username
    Optional<Customer> findByUsername(String username);

}