package com.tka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tka.model.Product;
import com.tka.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	

	@PostMapping("/add-product") // HW---> into DB this sholud go
	public String addProduct(@ModelAttribute Product product, Model model) {
		System.err.println(">> addProduct()");
		String msg;
		boolean isAdded = productService.addProduct(product);
		if (isAdded) {
			msg = product.getName() + " is added successfully";
		} else {
			msg = "something went wrong";
		}
		List<Product> productList = productService.getAllProduct();
		model.addAttribute("productList", productList);
		model.addAttribute("msg", msg);
		return "allProductDetails";
	}

	@GetMapping("/get-all-products")
	public String getallProducts(Model model) {
		List<Product> productList = productService.getAllProduct();
		model.addAttribute("productList", productList);
		return "allProductDetails";
	}

	@GetMapping("/get-shop")
	public String getShopping(Model model) {
		List<Product> productList = productService.getAllProduct();
		model.addAttribute("productList", productList);
		return "shopping";
	}

//	@PostMapping("/add-to-cart")
//	public String addProductsToCart(@ModelAttribute Product product, Model model) {
//		return "shopping";
//	}

}
