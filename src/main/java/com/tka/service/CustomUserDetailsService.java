package com.tka.service;

import com.tka.dao.CustomerDao;
import com.tka.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional; // 1. Make sure to import Optional

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 2. Use the correct method and handle the Optional result
        Optional<Customer> customerOptional = customerDao.findByUsername(username);

        // 3. Check if the user exists and throw an exception if not
        if (customerOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        Customer c = customerOptional.get(); // Get the Customer object from the Optional

        // The rest of your logic remains the same
        List<GrantedAuthority> authorities = Arrays.stream(c.getRole().split(","))
                .map(r -> new SimpleGrantedAuthority(r.trim()))
                .collect(Collectors.toList());
        
        // Note: Spring Security's User object expects (username, password, authorities)
        // Using getUsername() here is more conventional than getEmail()
        return new User(c.getUsername(), c.getPassword(), authorities);
    }
}