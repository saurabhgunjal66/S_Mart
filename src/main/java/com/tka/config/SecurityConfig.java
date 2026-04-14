package com.tka.config;

import org.springframework.beans.factory.annotation.Autowired; // <-- Need Autowired
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider; // <-- Need Provider
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService; // <-- Need UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    // 1. Inject the UserDetailsService - THIS IS CRITICAL for authentication
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. Add the AuthenticationProvider Bean - THIS IS CRITICAL
    //    It connects your UserDetailsService and PasswordEncoder to Spring Security
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                // Public pages (Anyone can access)
                .requestMatchers("/", "/index", "/get-signup-page", "/register", "/add-customer",
                                 "/login", "/get-shop",
                                 "/css/**", "/images/**", "/js/**").permitAll()

                

                // SELLER (and ADMIN) pages/actions (Sellers or Admins)
                .requestMatchers("/add-product", "/get-all-products","/get-all-customer" /* Adjust as needed */)
                    .hasAnyRole("SELLER") // <-- ADDED SELLER ROLE HERE

                // AUTHENTICATED USER pages/actions (Any logged-in User, Seller, or Admin)
                .requestMatchers("/add-to-cart", "/checkout", "/get-customer",
                                 "/my-account" /* etc */).permitAll()

                // Fallback: Secure everything else not explicitly permitted
                // 4. FIX: Changed from permitAll() back to authenticated()
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            // Logout Configuration
            .logout(logout -> logout
                .logoutUrl("/logout") // URL that triggers logout
                .logoutSuccessUrl("/login?logout") // Redirect after logout
                .invalidateHttpSession(true) // Invalidate session
                .deleteCookies("JSESSIONID") // Delete session cookie
                .permitAll()
            )
            .csrf(csrf -> csrf.disable()); // Keep CSRF disabled for now

        return http.build();
    }
}

