package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cg.dto.ProductCatalog;
import com.cg.entity.Product;
import com.cg.service.CatalogService;

@RestController
@RequestMapping("/catalogs")
public class CatalogController {

	@Autowired
	CatalogService service;

	@GetMapping("/products")
	public List<ProductCatalog> getAll() {
		return service.getAll();
	}

	@GetMapping("/products/{id}")
	public ProductCatalog getById(@PathVariable Long id) {
		return service.getById(id);
	}

	@GetMapping("/products/category/{category}")
	public List<ProductCatalog> getByCategory(@PathVariable String category) {
		return service.getByCategory(category);
	}

	@PostMapping("/products")
	public Product save(@RequestBody Product p) {
		return service.save(p);
	}
}
