package com.tka.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.dao.ProductDao;
import com.tka.model.Product;

@Service
public class ProductService {

	@Autowired
	ProductDao productDao;

	public List<Product> getAllProduct() {
		List<Product> all = productDao.findAll();
		List<Product> productList = new ArrayList<>();

		for (Product product : all) {
			String url = "../images/" + product.getName().toLowerCase() + ".jpg";
			product.setImageUrl(url);
			productList.add(product);
		}

		return productList;
	}

	public boolean addProduct(Product product) {
		Product save = productDao.save(product);
		if (save != null) {
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public Product getProductById(int productId) {
		
		return productDao.getById(productId);
	}

	

}
