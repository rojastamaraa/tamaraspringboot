package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping({"", "/"})
	public String showProducts(Model model) {
		model.addAttribute("products", productService.getProducts());
		return "products/index";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("product", new Product());
		return "products/CreateProduct";
	}
	
	@PostMapping
	public String save(Product product) {
		productService.create(product);
		return "redirect:/products";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
	model.addAttribute("product", productService.getProduct(id));
	return "products/EditProduct";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		productService.delete(id);
		return "redirect:/products";
	}
	
}
