package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product create(Product persona) {
        return productRepository.save(persona);
    }

    public Product getProduct(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }
}
