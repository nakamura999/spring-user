package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ProductController {
	private final ProductRepository repository;
	
	@GetMapping("/product/list")
	public String productList(Model model) {
		model.addAttribute("products", repository.findAll());
		return "products";
	}
	
	@GetMapping("/add")
	public String addProduct(@ModelAttribute Product product) {
		return "proform";
	}
	
	@PostMapping("/process")
	public String process(@Validated @ModelAttribute Product product, BindingResult result) {
		if (result.hasErrors()) {
			return "proform";
		}
		repository.save(product);
		return "redirect:/";
	}
	
	@GetMapping("/product/edit/{id}")
	public String editProduct(@PathVariable Long id, Model model) {
		model.addAttribute("product", repository.findById(id));
		return "proform";
	}
	
	@GetMapping("/product/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		repository.deleteById(id);
		return "redirect:/";
	}
}
