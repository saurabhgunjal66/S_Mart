
package com.tka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {
        // placeholders; real data can be added later
        model.addAttribute("totalCustomers", 0);
        model.addAttribute("totalProducts", 0);
        model.addAttribute("totalOrders", 0);
        return "adminDashboard";
    }
}
