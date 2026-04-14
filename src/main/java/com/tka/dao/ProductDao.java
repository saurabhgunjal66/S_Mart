package com.tka.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tka.model.Product;

public interface ProductDao extends JpaRepository<Product, Integer>{

}
