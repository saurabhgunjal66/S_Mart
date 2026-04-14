package com.tka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Sends a simple plain text email.
     * @param to The recipient's email address.
     * @param subject The subject of the email.
     * @param text The body content of the email.
     */
    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            // Set the 'from' address. This should match the username in your application.properties
            message.setFrom("your-email@gmail.com"); 
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
            System.out.println("Email sent successfully to " + to);
        } catch (Exception e) {
            // In a real application, you should use a proper logger here
            System.err.println("Error sending email to " + to + ": " + e.getMessage());
        }
    }
}
