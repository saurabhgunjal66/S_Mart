package com.tka.controller;

import java.security.Principal; // Import Principal if needed later
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // 1. Import RedirectAttributes

import com.tka.model.Customer;
import com.tka.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	CustomerService customerService;
	// String msg; // 2. REMOVED: Unsafe class-level variable

	@GetMapping("/")
	public String showHomePage() {
	 return "index";
	}
	
	@GetMapping("/login") 
	public String showLoginPage() { return "login";}

    // 3. ADDED: Method to SHOW the registration page
    @GetMapping("/get-signup-page")
    public String showRegistrationPage(@RequestParam(name="role", defaultValue="USER") String roleParam, Model model) {
        // Pass the role type (USER or SELLER) to the JSP, mainly for the title/heading
        model.addAttribute("roleParam", roleParam.toUpperCase()); 
        return "register"; // Renders register.jsp
    }
	
    // 4. UPDATED: Method to PROCESS registration
	@PostMapping("/register") // Changed mapping from /add-customer
	public String processRegistration(@ModelAttribute Customer customer, 
                                      @RequestParam("role") String role, // Get role from hidden field
                                      RedirectAttributes redirectAttributes) { // Use RedirectAttributes
		System.err.println(">> processRegistration()");

        // 5. Set the role based on the submitted value
        // Basic validation - default to USER if value is unexpected
        String assignedRole = "ROLE_USER";
        if ("ROLE_SELLER".equalsIgnoreCase(role)) {
            assignedRole = "ROLE_SELLER";
        }
        customer.setRole(assignedRole); 

		boolean isAdded = customerService.addCustomer(customer); // Service handles saving

        String msg; // Use local variable
		if (isAdded) {
			msg = "Registration successful! Please log in.";
		} else {
            // Consider adding more specific error messages (e.g., username exists)
			msg = "Registration failed. Please try again.";
		}

        // 6. Use Flash Attribute to send message to the login page after redirect
		redirectAttributes.addFlashAttribute("msg", msg);

		return "redirect:/login"; // Redirect to login page
	}

	@GetMapping("/get-customer")
	public String getCustomer(Model model) {
		// This likely needs logic to get the currently logged-in user's details
		System.err.println(">> getCustomer()");
		return "customerDetails";
	}

	@GetMapping("/get-all-customer")
	public String getallCustomer(Model model) {
		List<Customer> customerList = customerService.getallCustomer();
		model.addAttribute("customerList", customerList);
		return "allCustomerDetails";
	}
}

