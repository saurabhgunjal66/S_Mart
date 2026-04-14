package com.tka.controller;

import java.security.Principal; // 1. Import Principal
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // 2. Import RedirectAttributes

import com.tka.model.CartItem;
import com.tka.model.Customer;
import com.tka.service.CartService;
import com.tka.service.CustomerService;
import com.tka.service.EmailService;

@Controller
public class CartController {

    @Autowired
    CartService cartService;
    @Autowired EmailService emailService;
    @Autowired CustomerService customerService;
    /**
     * Controller for adding items to the cart.
     */
    @PostMapping("/add-to-cart")
    public String addProductToCart(@RequestParam("productId") int productId, // 3. Use Long for ID
                                   @RequestParam("quantity") int quantity,
                                   Principal principal, // 4. Add Principal
                                   RedirectAttributes redirectAttributes) {

        if (principal == null) {
            // User isn't logged in
            return "redirect:/login";
        }

        // 5. Get the username
        String username = principal.getName();

        // 6. Call the service with the CORRECT arguments (username, Long, int)
        cartService.addProductToCart(username, productId, quantity);

        redirectAttributes.addFlashAttribute("successMessage", "Item added to your cart!");

        return "redirect:/get-shop";
    }

    /**
     * Controller for generating the bill.
     */
    @GetMapping("/checkout")
    public String generateBill(Model model, Principal principal) { // 7. Add Principal

        if (principal == null) {
            // User isn't logged in
            return "redirect:/login";
        }

        // 8. Get the username
        String username = principal.getName();

        // 9. Call the service with the CORRECT argument (username)
        List<CartItem> allCartItems = cartService.getCartProducts(username);

        double total = 0;
        for (CartItem cartItem : allCartItems) {
            // Ensure getProductPrice accepts Long if your IDs are Long
            double productPrice = cartService.getProductPrice(cartItem.getProduct().getId());
            total += productPrice * cartItem.getQuantity(); // Calculate total correctly
        }

        model.addAttribute("total", total);
        model.addAttribute("cartItems", allCartItems);

        return "billDetails";
    }
    
    
    /**
     * 4. NEW METHOD: Handles the "Email Bill" button click.
     */
    @GetMapping("/email-bill")
    public String emailBill(Principal principal, RedirectAttributes redirectAttributes) {
        if (principal == null) {
            return "redirect:/login";
        }

        String username = principal.getName();
        
        // --- 1. Get User and Cart Data ---
        Customer customer = customerService.getCustomerByUsername(username); // You need this method in CustomerService
        List<CartItem> cartItems = cartService.getCartProducts(username);
        double total = calculateTotal(cartItems);

        // --- 2. Build the Email Content ---
        String emailBody = buildEmailBody(customer, cartItems, total);

        // --- 3. Send the Email ---
        emailService.sendSimpleMessage(
            customer.getEmail(),
            "Your ZenMart Order Confirmation",
            emailBody
        );

        // --- 4. Redirect back to the checkout page with a success message ---
        redirectAttributes.addFlashAttribute("emailStatus", "Bill has been sent to your email!");
        return "redirect:/checkout";
    }
    
    /**
     * Helper method to calculate the total bill amount.
     */
    private double calculateTotal(List<CartItem> cartItems) {
        double total = 0.0;
        for (CartItem item : cartItems) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    /**
     * Helper method to build a plain text email body for the bill.
     */
    private String buildEmailBody(Customer customer, List<CartItem> cartItems, double total) {
        StringBuilder billText = new StringBuilder();
        billText.append("Hello, ").append(customer.getName()).append("!\n\n");
        billText.append("Thank you for your order with ZenMart. Here is your bill summary:\n\n");
        billText.append("------------------------------------------\n");
        billText.append(String.format("%-20s | %-5s | %-10s\n", "Product", "Qty", "Subtotal"));
        billText.append("------------------------------------------\n");

        for (CartItem item : cartItems) {
            double subtotal = item.getProduct().getPrice() * item.getQuantity();
            billText.append(String.format("%-20s | %-5d | ₹%-9.2f\n",
                item.getProduct().getName(),
                item.getQuantity(),
                subtotal));
        }

        billText.append("------------------------------------------\n");
        billText.append(String.format("GRAND TOTAL: ₹%.2f\n\n", total));
        billText.append("We hope to see you again soon!\n\n");
        billText.append("- The ZenMart Team");

        return billText.toString();
    }

}