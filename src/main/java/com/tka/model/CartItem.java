package com.tka.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Use Long for IDs

    private int quantity;

    // --- THIS IS THE FIX ---
    // 1. REMOVE: @ManyToOne private int productId;
    // 2. ADD THIS BLOCK: This tells JPA to link to the 'Product' entity
    @ManyToOne
    @JoinColumn(name = "product_id") // This is the foreign key column in your 'cart_item' table
    private Product product;

    // 3. ADD THIS BLOCK: You also need a relationship to the user (Customer)
    @ManyToOne
    @JoinColumn(name = "user_id") // This is the foreign key column in your 'cart_item' table
    private Customer user;

    // --- Getters and Setters ---
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // 4. YOU MUST HAVE GETTERS/SETTERS FOR THE NEW ENTITY FIELDS
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getUser() {
        return user;
    }
    public void setUser(Customer user) {
        this.user = user;
    }
}