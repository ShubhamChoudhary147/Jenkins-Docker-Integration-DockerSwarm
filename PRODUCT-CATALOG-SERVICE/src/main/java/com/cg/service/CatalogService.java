package com.cg.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.client.RestTemplate;

import com.cg.dto.ProductCatalog;
import com.cg.entity.Product;
import com.cg.repository.ProductRepository;

@Service
public class CatalogService {

	@Autowired
	ProductRepository repo;
	
	@Autowired
	RestTemplate restTemplate;

	public CatalogService(ProductRepository repo) {
		this.repo = repo;
	}

	private ProductCatalog build(Product product) {
		Long pid = product.getPid();

		Map price = restTemplate.getForObject("http://PRICING-SERVICE/price/" + pid, Map.class);
		Map inventory = restTemplate.getForObject("http://INVENTORY-SERVICE/stock/" + pid, Map.class);

		ProductCatalog pc = new ProductCatalog();
		pc.pid = pid;
		pc.pname = product.getPname();
		pc.pcategory = product.getPcategory();
		pc.discountedPrice = price == null ? 0 : ((Number) price.get("discountedPrice")).doubleValue();
		pc.noOfItems = inventory == null ? 0 : ((Number) inventory.get("noOfItemsLeft")).intValue();

		return pc;
	}

	public List<ProductCatalog> getAll() {
		List<ProductCatalog> list = new ArrayList<>();
		for (Product p : repo.findAll())
			list.add(build(p));
		return list;
	}

	public ProductCatalog getById(Long id) {
		Product p = repo.findById(id).orElse(null);
		return p == null ? null : build(p);
	}

	public List<ProductCatalog> getByCategory(String category) {
		List<ProductCatalog> list = new ArrayList<>();
		for (Product p : repo.findByPcategory(category))
			list.add(build(p));
		return list;
	}

	public Product save(Product p) {
		return repo.save(p);
	}
}
