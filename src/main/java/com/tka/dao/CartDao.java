package com.tka.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tka.model.CartItem;
import com.tka.model.Customer;
import com.tka.model.Product;

@Repository
public interface CartDao extends JpaRepository<CartItem, Integer> {

	// Spring Data JPA automatically creates the query: "SELECT c FROM CartItem c WHERE c.user = :user"
    List<CartItem> findByUser(Customer user);

    // Optional: Find a specific item for a specific user (useful for updating quantity)
    Optional<CartItem>  findByUserAndProduct(Customer user, Product product);
	

}
