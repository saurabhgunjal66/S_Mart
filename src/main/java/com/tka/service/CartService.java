package com.tka.service;

import java.util.List;
import java.util.Optional; // Import Optional

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Import Transactional

// Assuming your repository is named CartDao and has the required methods
import com.tka.dao.CartDao; 
import com.tka.dao.CustomerDao; // Import CustomerDao
import com.tka.model.CartItem;
import com.tka.model.Customer; // Import Customer
import com.tka.model.Product;

@Service
@Transactional // Good practice for service methods modifying data
public class CartService {

    // Use constructor injection (recommended)
    private final CartDao cartDao;
    private final CustomerDao customerDao;
    private final ProductService productService; // Assuming this fetches Product details

    @Autowired
    public CartService(CartDao cartDao, CustomerDao customerDao, ProductService productService) {
        this.cartDao = cartDao;
        this.customerDao = customerDao;
        this.productService = productService;
    }

    /**
     * Adds a product to a specific user's cart or updates the quantity.
     * * @param username The username of the logged-in user.
     * @param productId The ID of the product to add.
     * @param quantity The quantity to add.
     */
    public void addProductToCart(String username, int productId, int quantity) {
        // 1. Find the User and Product
        Customer user = customerDao.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found: " + username));
            
        // Use ProductService to get the Product entity
        Product product = productService.getProductById(productId); 
        if (product == null) {
            throw new RuntimeException("Product not found: " + productId);
        }

        // 2. Check if the item already exists for this user
        // Assumes CartDao has findByUserAndProduct(Customer user, Product product)
        Optional<CartItem> existingItemOpt = cartDao.findByUserAndProduct(user, product); 

        if (existingItemOpt.isPresent()) {
            // Update quantity
            CartItem existingItem = existingItemOpt.get();
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartDao.save(existingItem);
        } else {
            // Create a new item and LINK IT TO THE USER and PRODUCT
            CartItem newItem = new CartItem();
            newItem.setUser(user); // Link to the specific user
            newItem.setProduct(product); // Link to the specific product
            newItem.setQuantity(quantity);
            cartDao.save(newItem);
        }
    }

    /**
     * Gets all cart items ONLY for the specified user.
     * * @param username The username of the logged-in user.
     * @return A list of cart items for that user.
     */
    public List<CartItem> getCartProducts(String username) {
        // Find the user first
        Customer user = customerDao.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found: " + username));
            
        // Use the repository method to find items by user
        // Assumes CartDao has findByUser(Customer user)
        return cartDao.findByUser(user);
    }

    /**
     * Gets the price of a single product.
     * (The total cost calculation belongs in the controller/JSP based on cart quantity)
     * * @param productId The ID of the product.
     * @return The price of the product, or 0.0 if not found.
     */
    public double getProductPrice(int productId) { // Renamed for clarity
        Product product = productService.getProductById(productId);
        // Return 0 if product doesn't exist to avoid errors
        return (product != null) ? product.getPrice() : 0.0; 
    }

	public void addProductToCart(Product productId, int quantity) {
		// TODO Auto-generated method stub
		
	}

	public double getProductTotalCost(int id) {
		// TODO Auto-generated method stub
		return 0;
	}
}